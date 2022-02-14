package gredal.simon.carsrus.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                ex.getStatus().value(),
                ex.getClass().getSimpleName(),
                ex.getReason()
        );

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    private record ErrorResponse(String path, Integer status, String exception, String reason) {}
}

