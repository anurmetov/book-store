package mate.academy.repository;

import java.util.Optional;
import mate.academy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByShippingAddress(String shippingAddress);
}
