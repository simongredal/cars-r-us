package gredal.simon.carsrus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MemberNotFoundException extends ResponseStatusException {
    public MemberNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public MemberNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public MemberNotFoundException(String reason, Throwable cause) {
        super(HttpStatus.NOT_FOUND, reason, cause);
    }
}
