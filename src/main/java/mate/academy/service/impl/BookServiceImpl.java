package mate.academy.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.BookDtoWithoutCategoryIds;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.exception.EntityNotFoundException;
import mate.academy.mapper.BookMapper;
import mate.academy.model.Book;
import mate.academy.model.Category;
import mate.academy.repository.BookRepository;
import mate.academy.repository.BookSpecificationBuilder;
import mate.academy.repository.CategoryRepository;
import mate.academy.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        categoryRepository.saveAll(createBookRequestDto.getCategories());

        Book book = bookMapper.toModel(createBookRequestDto);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book was not found by id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book was not found by id: " + id));

        bookMapper.updateBookFromRequest(requestDto, book);

        Set<Category> categories = new HashSet<>();

        for (Category requestCategory : requestDto.getCategories()) {
            Category category = categoryRepository
                    .findByName(requestCategory.getName())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(requestCategory.getName());
                        return newCategory;
                    });

            category.setDescription(requestCategory.getDescription());

            categories.add(categoryRepository.save(category));
        }

        book.getCategories().clear();
        book.getCategories().addAll(categories);

        return bookMapper.toDto(book);
    }

    @Override
    public Page<BookDto> searchBooks(BookSearchParametersDto searchParameters, Pageable pageable) {
        Specification<BookDto> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification, pageable);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllBooksByCategoryId(Long categoryId) {
        return bookRepository
                .findAllByCategories_Id(categoryId)
                .stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }
}
