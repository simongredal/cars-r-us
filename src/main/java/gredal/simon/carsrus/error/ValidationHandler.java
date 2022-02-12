package gredal.simon.carsrus.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                request.getRequestURI(),
                "%d %s".formatted(ex.getStatus().value(), ex.getStatus().getReasonPhrase()),
                ex.getClass().getSimpleName(),
                ex.getReason()
        );

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @AllArgsConstructor
    @Getter @Setter
    @ToString
    private static class ErrorResponse {
        private String path;
        private String status;
        private String exception;
        private String reason;
    }

}

