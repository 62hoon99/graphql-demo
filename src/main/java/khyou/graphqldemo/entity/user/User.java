package khyou.graphqldemo.entity.user;

import khyou.graphqldemo.entity.SearchResult;
import khyou.graphqldemo.entity.cart.Cart;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
public class User implements SearchResult {
    // Getters and setters
    private final String id;
    private final String name;
    private final String email;
    private final OffsetDateTime createdAt;
    @Setter
    private Cart cart;

    public User(String id, String name, String email, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.cart = null; // 초기값은 null로 설정
    }

}
