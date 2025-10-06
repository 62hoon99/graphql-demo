package khyou.graphqldemo.entity.cart;

import khyou.graphqldemo.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Cart {
    // Getters and setters
    private final String id;
    private final User user;
    @Setter
    private double totalAmount = 0.0;
    @Setter
    private List<CartItem> items = List.of();

    public Cart(String id, User user) {
        this.id = id;
        this.user = user;
    }

}