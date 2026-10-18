package mate.academy.service;

import java.util.List;
import mate.academy.dto.CreateOrderInfoDto;
import mate.academy.dto.OrderResponseDto;
import mate.academy.dto.UpdateOrderStatusRequestDto;

public interface OrderService {

    void createOrder(CreateOrderInfoDto createOrderInfoDto);

    List<OrderResponseDto> getOrderHistory();

    void updateOrderStatus(Long id, UpdateOrderStatusRequestDto updateOrderStatusRequestDto);

}
