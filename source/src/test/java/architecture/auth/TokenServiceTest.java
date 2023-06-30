package architecture.auth;

import architecture.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TokenServiceTest {
    @Autowired
    private TokenService service;

    @Test
    void verifyNullTokenShouldReturnFalse() {
        final var result = service.verify(null);

        assertFalse(result);
    }

    @Test
    void verifyEmptyTokenShouldReturnFalse() {
        final var result = service.verify("");

        assertFalse(result);
    }

    @Test
    void verifyInvalidTokenShouldReturnFalse() {
        final var result = service.verify(UUID.randomUUID().toString());

        assertFalse(result);
    }

    @Test
    void verifyValidTokenShouldReturnTrue() {
        final var token = service.create(Constants.user);

        final var result = service.verify(token);

        assertTrue(result);
    }
}
