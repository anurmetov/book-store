package mate.academy.dto;

import jakarta.validation.constraints.Positive;

public record UpdateCartItemDto(
        @Positive
        int quantity
) {
}
