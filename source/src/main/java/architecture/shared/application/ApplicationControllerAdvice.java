package architecture.shared.application;

import architecture.shared.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Comparator;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public final class ApplicationControllerAdvice {
    private final MessageService messageService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageService.get("error"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageService.get("unauthorized"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException exception) {
        final var message = exception
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .sorted(Comparator.comparing(FieldError::getField))
            .map(error -> Character.toUpperCase(error.getField().charAt(0)) + error.getField().substring(1) + " " + error.getDefaultMessage() + ".")
            .collect(Collectors.joining(" "));

        return ResponseEntity.badRequest().body(message);
    }
}
