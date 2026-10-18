package mate.academy.repository;

import java.util.List;
import mate.academy.model.Order;
import mate.academy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder_Id(Long orderId);

    OrderItem findByIdAndOrder_Id(Long id, Long orderId);

    Long order(Order order);
}
