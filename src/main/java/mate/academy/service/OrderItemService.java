package mate.academy.service;

import java.util.List;
import mate.academy.dto.OrderItemResponseDto;

public interface OrderItemService {

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getSpecificOrderItemFromOrder(Long orderId, Long orderItemId);
}
