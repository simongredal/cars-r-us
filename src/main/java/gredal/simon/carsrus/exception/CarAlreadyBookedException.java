package gredal.simon.carsrus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class CarAlreadyBookedException extends ResponseStatusException {
    public CarAlreadyBookedException() {
        super(HttpStatus.CONFLICT);
    }

    public CarAlreadyBookedException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }

    public CarAlreadyBookedException(String reason, Throwable cause) {
        super(HttpStatus.CONFLICT, reason, cause);
    }
}
