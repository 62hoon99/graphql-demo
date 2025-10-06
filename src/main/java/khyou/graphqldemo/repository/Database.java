package khyou.graphqldemo.repository;

import khyou.graphqldemo.entity.cart.Cart;
import khyou.graphqldemo.entity.cart.CartItem;
import khyou.graphqldemo.entity.product.Clothing;
import khyou.graphqldemo.entity.product.Electronics;
import khyou.graphqldemo.entity.product.Product;
import khyou.graphqldemo.entity.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Database {

    private static class SingleInstanceHolder {
        private static final Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    public final List<Product> products = new ArrayList<>(Arrays.asList(
            new Electronics(
                    "1",
                    "mac book air",
                    1000.0,
                    "3years"
            ),
            new Electronics(
                    "2",
                    "iphone 15",
                    2000.0,
                    "2years"
            ),
            new Electronics(
                    "3",
                    "samsung tv",
                    3000.0,
                    "1year"
            ),
            new Clothing(
                    "5",
                    "T-shirt",
                    3000.0,
                    "M"
            ),
            new Clothing(
                    "6",
                    "Jeans",
                    3000.0,
                    "XL"
            ),
            new Clothing(
                    "7",
                    "Dress",
                    3000.0,
                    "S"
            )
    ));

    public final List<User> users = new ArrayList<>(Arrays.asList(
            new User(
                    "1",
                    "John Doe",
                    "john.doe@example.com",
                    OffsetDateTime.now().minusDays(35)
            ),
            new User(
                    "2",
                    "Jane Smith",
                    "jane.smith@example.com",
                    OffsetDateTime.now().minusDays(50)
            )
    ));

    public final List<Cart> carts = new ArrayList<>(Arrays.asList(
            new Cart(
                    "1",
                    users.stream().filter(user -> user.getId().equals("1")).findFirst().orElse(null)
            ),
            new Cart(
                    "2",
                    users.stream().filter(user -> user.getId().equals("2")).findFirst().orElse(null)
            )
    ));

    public final List<CartItem> cartItems = new ArrayList<>(Arrays.asList(
            new CartItem(
                    "1",
                    1,
                    products.stream().filter(product -> product.getId().equals("1")).findFirst().orElse(null),
                    carts.stream().filter(cart -> cart.getId().equals("1")).findFirst().orElse(null)
            ),
            new CartItem(
                    "2",
                    2,
                    products.stream().filter(product -> product.getId().equals("2")).findFirst().orElse(null),
                    carts.stream().filter(cart -> cart.getId().equals("1")).findFirst().orElse(null)
            ),
            new CartItem(
                    "3",
                    1,
                    products.stream().filter(product -> product.getId().equals("2")).findFirst().orElse(null),
                    carts.stream().filter(cart -> cart.getId().equals("2")).findFirst().orElse(null)
            ),
            new CartItem(
                    "4",
                    1,
                    products.stream().filter(product -> product.getId().equals("5")).findFirst().orElse(null),
                    carts.stream().filter(cart -> cart.getId().equals("2")).findFirst().orElse(null)
            ),
            new CartItem(
                    "5",
                    1,
                    products.stream().filter(product -> product.getId().equals("6")).findFirst().orElse(null),
                    carts.stream().filter(cart -> cart.getId().equals("2")).findFirst().orElse(null)
            )
    ));
}
