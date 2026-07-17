package mate.academy.service;

import java.util.List;
import mate.academy.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Book save(Book book);

    List findAll();
}
