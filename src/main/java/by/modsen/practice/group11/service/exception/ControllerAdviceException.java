package by.modsen.practice.group11.service.exception;

import by.modsen.practice.group11.model.dto.response.ErrorResponseTo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdviceException {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponseTo> catchThrowableException(Throwable e) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(takeError(e, httpStatus));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseTo> catchDataIntegrityViolationException(DataIntegrityViolationException e) {

        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(httpStatus).body(takeError(e, httpStatus));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseTo> catchMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(takeError(e, httpStatus));
    }

    @ExceptionHandler(ResourceStateException.class)
    public ResponseEntity<ErrorResponseTo> catchResourceStateException(ResourceStateException e) {

        HttpStatus httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(takeError(e));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseTo> catchResourceNotFoundException(ResourceNotFoundException e) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(httpStatus).body(takeError(e));
    }


    private static ErrorResponseTo takeError(Throwable e, HttpStatus httpStatus) {
        return new ErrorResponseTo(
                HttpStatus.BAD_REQUEST.value() * 100 + 1,
                "Throwable exception",
                new String[] {e.getMessage()}
        );
    }

    private static ErrorResponseTo takeError(DataIntegrityViolationException e, HttpStatus httpStatus) {
        return new ErrorResponseTo(HttpStatus.FORBIDDEN.value() * 100 + 2, "DataIntegrityViolationException exception", new String[] {e.getMessage()});
    }

    private static ErrorResponseTo takeError(MethodArgumentNotValidException e, HttpStatus httpStatus) {
        List<String> messages = new ArrayList<>();

        for (ObjectError objectError : e.getAllErrors()) {
            if (objectError instanceof FieldError fieldError) {
                messages.add("FieldError " + fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                messages.add(objectError.toString());
            }
        }

        return new ErrorResponseTo(
                HttpStatus.BAD_REQUEST.value() * 100 + 3,
                "MethodArgumentNotValidException exception",
                messages.toArray(String[]::new)
        );
    }

    private static ErrorResponseTo takeError(ResourceStateException e) {
        return e.getErrorResponseTo();
    }

    private static ErrorResponseTo takeError(ResourceNotFoundException e) {
        return e.getErrorResponseTo();
    }
}
