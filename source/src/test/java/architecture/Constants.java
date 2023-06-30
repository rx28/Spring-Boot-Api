package architecture;

import architecture.user.User;

import java.util.UUID;

public class Constants {
    public static final String username = "admin";
    public static final String password = "123456";
    public static final User user = new User(UUID.randomUUID(), "Admin", "admin@admin.com", username, password);
}
