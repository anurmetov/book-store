package mate.academy.dto;

import java.util.List;
import mate.academy.model.OrderItem;

public record OrderResponseDto(
        Long id,
        Long userId,
        List<OrderItemResponseDto> orderItems,
        String orderDate,
        double total,
        String status
) {
}
