package by.modsen.practice.group11.model.dto.response;

import by.modsen.practice.group11.model.enums.Gender;

import java.time.LocalDate;
import java.util.UUID;

public record PersonalInfoResponse(
        UUID id,
        String fullName,
        Gender gender,
        LocalDate birthday
) { }
