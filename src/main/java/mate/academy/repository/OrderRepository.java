package mate.academy.repository;

import java.util.List;
import mate.academy.model.Order;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser_Id(Long userId);
}
