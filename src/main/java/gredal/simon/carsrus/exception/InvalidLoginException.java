package gredal.simon.carsrus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLoginException extends ResponseStatusException {
    public InvalidLoginException() {
        super(HttpStatus.NOT_ACCEPTABLE);
    }

    public InvalidLoginException(String reason) {
        super(HttpStatus.NOT_ACCEPTABLE, reason);
    }

    public InvalidLoginException(String reason, Throwable cause) {
        super(HttpStatus.NOT_ACCEPTABLE, reason, cause);
    }
}
