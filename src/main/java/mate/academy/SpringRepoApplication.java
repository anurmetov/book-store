package mate.academy;

import java.math.BigDecimal;
import mate.academy.entity.Book;
import mate.academy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRepoApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringRepoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book bookEntity = new Book();
            bookEntity.setAuthor("Author");
            bookEntity.setTitle("Hi");
            bookEntity.setIsbn("!");
            bookEntity.setPrice(BigDecimal.ONE);
            bookService.save(bookEntity);
            System.out.println(bookService.findAll());
        };
    }
}

