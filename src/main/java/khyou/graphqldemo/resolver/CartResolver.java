package khyou.graphqldemo.resolver;

import khyou.graphqldemo.entity.cart.Cart;
import khyou.graphqldemo.input.AddCartItemInput;
import khyou.graphqldemo.input.DeleteCartItemInput;
import khyou.graphqldemo.service.CartService;
import org.apache.coyote.BadRequestException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CartResolver {
    private final CartService cartService;

    public CartResolver(CartService cartService) {
        this.cartService = cartService;
    }

    @QueryMapping
    public Cart getUserCart(@Argument String userId) throws BadRequestException {
        return cartService.getUserCart(userId);
    }

    @MutationMapping
    public Cart addCartItem(@Argument AddCartItemInput addCartItemInput) throws BadRequestException {
        return cartService.addCartItem(addCartItemInput);
    }

    @MutationMapping
    public Cart deleteCartItem(@Argument DeleteCartItemInput deleteCartItemInput) throws BadRequestException {
        return cartService.deleteCartItem(deleteCartItemInput);
    }
}
