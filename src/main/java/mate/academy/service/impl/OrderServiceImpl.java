package mate.academy.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.CreateOrderInfoDto;
import mate.academy.model.CartItem;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import mate.academy.model.ShoppingCart;
import mate.academy.model.Status;
import mate.academy.model.User;
import mate.academy.repository.CartItemRepository;
import mate.academy.repository.OrderItemsRepository;
import mate.academy.repository.OrderRepository;
import mate.academy.repository.ShoppingCartRepository;
import mate.academy.repository.UserRepository;
import mate.academy.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Override
    public void createOrder(CreateOrderInfoDto createOrderInfoDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = userRepository.findUserByEmail(auth.getName()).orElseThrow();

        ShoppingCart userShoppingCart = shoppingCartRepository.findShoppingCartByUser_Id(currentUser.getId());
        Set<CartItem> userCartItems = userShoppingCart.getCartItems();
        BigDecimal total = new BigDecimal(0);
        for (CartItem c : userCartItems) {
            total = total.add(c.getBook().getPrice().multiply(BigDecimal.valueOf(c.getQuantity())));
        }

        Set<OrderItem> orderItems = new HashSet<>();


        BigDecimal finalTotal = total;
        Order order = Order
                .builder()
                .user(currentUser)
                .status(Status.PENDING)
                .total(finalTotal)
                .orderDate(LocalDateTime.now())
                .shippingAddress(createOrderInfoDto.shippingAddress())
                .orderItems(
                        orderItems
                ).build();

        for (CartItem c : userCartItems) {
            OrderItem orderItem = OrderItem
                    .builder()
                    .order(order)
                    .book(c.getBook())
                    .quantity(c.getQuantity())
                    .price(BigDecimal.valueOf(c.getQuantity()))
                    .build();
            orderItems.add(orderItem);

        }

        System.out.println(currentUser.getId());

        order.setOrderItems(orderItems);
        orderRepository.save(order);

    }

    @Override
    public List<Order> getOrderHistory() {
        return List.of();
    }
}
