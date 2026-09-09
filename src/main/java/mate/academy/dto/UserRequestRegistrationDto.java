package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;
import mate.academy.validation.FieldMatch;

@FieldMatch.List({
        @FieldMatch(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords do not match!"
        ),
})
public record UserRequestRegistrationDto(
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String repeatPassword,
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        String shippingAddress) {
}
