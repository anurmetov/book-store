package mate.academy.service;

import java.util.List;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;

public interface OrderItemService {

    List<OrderItem> getOrderItems(Long orderId);

    OrderItem getSpecificOrderItemFromOrder(Long orderId, Long orderItemId);
}
