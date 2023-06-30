package architecture.shared.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class Result<T> {
    private final ResultType type;
    private final String message;
    private final T value;

    private Result(ResultType type, String message, T value) {
        this.type = type;
        this.message = message;
        this.value = value;
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ResultType.ERROR, message, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultType.SUCCESS, null, null);
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(ResultType.SUCCESS, null, value);
    }

    public String getMessage() {
        return message;
    }

    public boolean hasMessage() {
        return message != null && !message.trim().isEmpty();
    }

    public boolean isError() {
        return type == ResultType.ERROR;
    }

    public boolean isSuccess() {
        return type == ResultType.SUCCESS;
    }

    public T getValue() {
        return value;
    }

    public boolean hasValue() {
        return value != null;
    }

    public ResponseEntity<Object> response() {
        if (isError()) return ResponseEntity.badRequest().body(message);

        if (hasValue()) return ResponseEntity.ok(getValue());

        final var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        final var status = request.getMethod().equals("GET") ? HttpStatus.NOT_FOUND : HttpStatus.NO_CONTENT;

        return ResponseEntity.status(status).build();
    }
}
