package mate.academy.service;

import java.util.List;
import mate.academy.dto.OrderItemResponseDto;
import mate.academy.dto.OrderResponseDto;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;

public interface OrderItemService {

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getSpecificOrderItemFromOrder(Long orderId, Long orderItemId);
}
