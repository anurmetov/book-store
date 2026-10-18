package mate.academy.service;

import java.util.List;
import mate.academy.dto.CreateOrderInfoDto;
import mate.academy.dto.OrderResponseDto;
import mate.academy.model.Order;

public interface OrderService {

    void createOrder(CreateOrderInfoDto createOrderInfoDto);

    List<OrderResponseDto> getOrderHistory();
}
