package mate.academy.springrepo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "jwt.secret=secretsdasdaadsadsasdsdaadsadsasdadsasd",
        "jwt.expiration=30000000000"
}
)
class SpringRepoApplicationTests {

    @Test
    void contextLoads() {
    }

}
