package mate.academy.repository.custom;

import java.util.List;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.model.Book;

public interface BookRepositoryCustom {

    List<Book> findAll(BookSearchParametersDto bookSearchParametersDto);
}
