package mate.academy.dto;

import java.util.List;

public record OrderResponseDto(
        Long id,
        Long userId,
        List<OrderItemResponseDto> orderItems,
        String orderDate,
        double total,
        String status
) {
}
