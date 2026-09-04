package mate.academy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
@Tag(name = "Book Management", description = "API for managing books")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "Get all books", description = "Get all books from DB")
    @GetMapping
    public Page<BookDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Get book by id", description = "Get book by id from DB")
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @Operation(summary = "Add a new book", description = "Add a new book to DB")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }

    @Operation(summary = "Delete book by id", description = "Delete book by id from DB")
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @Operation(summary = "Update existing book by id",
            description = "Update existing book by id from DB")
    @PutMapping("/{id}")
    public BookDto updateBookById(@PathVariable Long id,
                               @RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.updateById(id, createBookRequestDto);
    }

    @Operation(summary = "Search books with dynamic parameters",
            description = "Search books with dynamic parameters and get them from DB")
    @GetMapping("/search")
    public Page<BookDto> searchBooks(
            @Valid BookSearchParametersDto searchParameters,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.searchBooks(searchParameters, pageable);
    }

}
