package architecture.auth;

import architecture.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    private AuthService service;

    @Test
    void authNullRequestShouldReturnError() {
        final var result = service.auth(new AuthRequest(null, null));

        assertTrue(result.isError());
    }

    @Test
    void authEmptyRequestShouldReturnError() {
        final var result = service.auth(new AuthRequest("", ""));

        assertTrue(result.isError());
    }

    @Test
    void authInvalidUsernameShouldReturnError() {
        final var request = new AuthRequest(UUID.randomUUID().toString(), Constants.password);

        final var result = service.auth(request);

        assertTrue(result.isError());
    }

    @Test
    void authInvalidPasswordShouldReturnError() {
        final var request = new AuthRequest(Constants.username, UUID.randomUUID().toString());

        final var result = service.auth(request);

        assertTrue(result.isError());
    }

    @Test
    void authValidRequestShouldReturnSuccess() {
        final var request = new AuthRequest(Constants.username, Constants.password);

        final var result = service.auth(request);

        assertTrue(result.isSuccess());
    }
}
