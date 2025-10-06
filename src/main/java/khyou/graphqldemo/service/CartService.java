package khyou.graphqldemo.service;

import khyou.graphqldemo.entity.cart.Cart;
import khyou.graphqldemo.entity.cart.CartItem;
import khyou.graphqldemo.entity.user.User;
import khyou.graphqldemo.input.AddCartItemInput;
import khyou.graphqldemo.input.DeleteCartItemInput;
import khyou.graphqldemo.repository.Database;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    public Cart getUserCart(String userId) throws BadRequestException {
        return Database.getInstance().carts.stream()
                .filter(cart -> cart.getUser().getId().equals(userId))
                .findFirst()
                .map(cart -> {
                    cart.setItems(Database.getInstance().cartItems.stream()
                            .filter(cartItem -> cartItem.cart().getId().equals(cart.getId()))
                            .toList());
                    cart.setTotalAmount(cart.getItems().stream()
                            .mapToDouble(item -> item.product().getPrice() * item.quantity())
                            .sum());
                    return cart;
                })
                .orElseThrow(() -> new BadRequestException("Cart not found"));
    }

    public Cart addUserCart(User user) {
        Cart cart = new Cart(
                UUID.randomUUID().toString().substring(0, 5),
                user
        );
        Database.getInstance().carts.add(cart);
        return cart;
    }

    public Cart addCartItem(AddCartItemInput addCartItemInput) throws BadRequestException {
        CartItem cartItem = new CartItem(
                UUID.randomUUID().toString().substring(0, 5),
                addCartItemInput.getQuantity(),
                productService.getProduct(addCartItemInput.getProductId()),
                getUserCart(addCartItemInput.getUserId())
        );
        Database.getInstance().cartItems.add(cartItem);
        return getUserCart(addCartItemInput.getUserId());
    }

    public Cart deleteCartItem(DeleteCartItemInput deleteCartItemInput) throws BadRequestException {
        Database.getInstance().cartItems.removeIf(cartItem -> cartItem.id().equals(deleteCartItemInput.getCartItemId()));
        return getUserCart(deleteCartItemInput.getUserId());
    }

}
