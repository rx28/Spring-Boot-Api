package architecture.user;

import lombok.Data;

import java.util.UUID;

@Data
public final class UserModel {
    private UUID id;
    private String name;
    private String email;
}
