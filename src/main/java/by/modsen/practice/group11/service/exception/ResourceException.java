package by.modsen.practice.group11.service.exception;

import by.modsen.practice.group11.model.dto.response.ErrorResponseTo;
import lombok.Getter;

@Getter
public class ResourceException extends RuntimeException{

    private final ErrorResponseTo errorResponseTo;

    public ResourceException(int code, String message) {
        super(message);
        errorResponseTo = new ErrorResponseTo(code, message, null);
    }
}
