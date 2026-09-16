package mate.academy.repository;

import java.util.Optional;
import mate.academy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    @Query("""
            SELECT u
            FROM User u
            LEFT JOIN FETCH u.roles
            WHERE u.email = :email
            """)
    Optional<User> findUserByEmail(String email);
}
