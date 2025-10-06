package khyou.graphqldemo.service;

import khyou.graphqldemo.entity.user.User;
import khyou.graphqldemo.input.AddUserInput;
import khyou.graphqldemo.repository.Database;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CartService cartService;

    public List<User> getUsers() {
        return Database.getInstance().users;
    }

    public User getUser(String userId) throws BadRequestException {
        return Database.getInstance().users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .map(user -> {
                    try {
                        user.setCart(cartService.getUserCart(userId));
                        return user;
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public User addUser(AddUserInput addUserInput) {
        User user = new User(
                UUID.randomUUID().toString().substring(0, 5),
                addUserInput.getName(),
                addUserInput.getEmail(),
                OffsetDateTime.now()
        );
        Database.getInstance().users.add(user);
        user.setCart(cartService.addUserCart(user));
        return user;
    }
}
