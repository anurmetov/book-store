package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestLoginDto(
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
