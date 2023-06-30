package architecture.shared.application;

import architecture.user.AddUserRequest;
import architecture.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class ApplicationEventListener {
    private final UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void executeOnStartup() {
        userService.add(new AddUserRequest("Admin", "admin@admin.com", "admin", "123456"));
    }
}
