package by.modsen.practice.group11.model.dto.response;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record JwtResponse(
        String tokenAccess,
        String tokenRefresh
) {}
