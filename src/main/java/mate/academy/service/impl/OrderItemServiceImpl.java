package mate.academy.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.OrderItemResponseDto;
import mate.academy.mapper.OrderMapper;
import mate.academy.model.OrderItem;
import mate.academy.model.User;
import mate.academy.repository.OrderItemsRepository;
import mate.academy.repository.UserRepository;
import mate.academy.service.OrderItemService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final UserRepository userRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderItemResponseDto> getOrderItems(Long orderId) {
        List<OrderItem> byOrderId = orderItemsRepository.findByOrder_Id((orderId));
        return byOrderId
                .stream()
                .map(orderMapper::itemToResponseDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getSpecificOrderItemFromOrder(Long orderId, Long orderItemId) {
        OrderItem orderItem = orderItemsRepository.findByIdAndOrder_Id(orderItemId, orderId);
        return orderMapper.itemToResponseDto(orderItem);
    }
}
