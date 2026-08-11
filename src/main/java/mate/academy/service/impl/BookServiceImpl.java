package mate.academy.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.BookMapper;
import mate.academy.model.Book;
import mate.academy.repository.BookRepository;
import mate.academy.service.BookService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toModel(createBookRequestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book was not found by id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        if (findBookById(id) != null) {
            bookRepository.deleteById(id);
        }
    }

}
