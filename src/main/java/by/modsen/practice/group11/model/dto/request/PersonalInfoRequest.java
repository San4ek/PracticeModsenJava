package by.modsen.practice.group11.model.dto.request;

import by.modsen.practice.group11.model.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PersonalInfoRequest(
        @NotNull
        @Size(max = 45, message = "Full name should not be longer than 45 characters")
        String fullName,

        @NotNull(message = "Gender is mandatory")
        @Schema(description = "Gender, it should be written with a capital letter", example = "MALE")
        Gender gender,

        @Past(message = "Birthday must be in the past")
        @Schema(description = "Your birthday, it should be written in this format dd:mm:yy", example = "23:08:2023")
        LocalDateTime birthday
) {
}
