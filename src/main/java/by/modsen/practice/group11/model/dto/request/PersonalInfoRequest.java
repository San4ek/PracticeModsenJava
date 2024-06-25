package by.modsen.practice.group11.model.dto.request;

import by.modsen.practice.group11.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PersonalInfoRequest(
        @NotNull
        @Size(max = 45, message = "Full name should not be longer than 45 characters")
        String fullName,

        @NotNull(message = "Gender is mandatory")
        Gender gender,

        @Past(message = "Birthday must be in the past")
        LocalDateTime birthday
) { }
