package by.modsen.practice.group11.model.dto.response;

public record ErrorResponseTo(
        int code,
        String message,
        String[] errorsMessages
) {

}
