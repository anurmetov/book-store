package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateOrderInfoDto(
        @NotBlank
        String shippingAddress
) {
}
