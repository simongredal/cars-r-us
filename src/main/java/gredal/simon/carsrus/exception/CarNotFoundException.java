package gredal.simon.carsrus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class CarNotFoundException extends ResponseStatusException {
    public CarNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public CarNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public CarNotFoundException(String reason, Throwable cause) {
        super(HttpStatus.NOT_FOUND, reason, cause);
    }
}
