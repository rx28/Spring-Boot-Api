package architecture.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public final class AuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Object> auth(@RequestBody @Valid AuthRequest request) {
        return authService.auth(request).response();
    }
}
