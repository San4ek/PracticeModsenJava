package by.modsen.practice.group11.service.exception;

public class ResourceNotFoundException extends ResourceException{

    public ResourceNotFoundException(int code, String message) {
        super(code, message);
    }
}
