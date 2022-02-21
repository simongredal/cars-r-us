package gredal.simon.carsrus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailInvalidException extends ResponseStatusException {
    public EmailInvalidException() {
        super(HttpStatus.CONFLICT);
    }

    public EmailInvalidException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }

    public EmailInvalidException(String reason, Throwable cause) {
        super(HttpStatus.CONFLICT, reason, cause);
    }
}
