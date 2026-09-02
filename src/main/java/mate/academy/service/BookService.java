package mate.academy.service;

import java.util.List;
import mate.academy.dto.BookDto;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.dto.CreateBookRequestDto;

public interface BookService {

    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> findAll();

    BookDto findBookById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto createBookRequestDto);

    List<BookDto> searchBooks(BookSearchParametersDto searchParameters);
}
