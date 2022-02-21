package gredal.simon.carsrus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReservationNotFoundException extends ResponseStatusException {
    public ReservationNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public ReservationNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public ReservationNotFoundException(String reason, Throwable cause) {
        super(HttpStatus.NOT_FOUND, reason, cause);
    }
}
