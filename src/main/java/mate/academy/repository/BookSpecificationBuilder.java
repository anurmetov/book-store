package mate.academy.repository;

import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookDto;
import mate.academy.dto.BookSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<BookDto> {
    private final SpecificationProviderManager<BookDto> bookDtoSpecificationProviderManager;

    @Override
    public Specification<BookDto> build(BookSearchParametersDto bookSearchParametersDto) {
        Specification<BookDto> spec = Specification.allOf();
        if (bookSearchParametersDto.isbn() != null) {
            spec = spec.and(bookDtoSpecificationProviderManager.getSpecificationProvider("isbn")
                    .getSpecification(bookSearchParametersDto.isbn()));
        }

        if (bookSearchParametersDto.author() != null) {
            spec = spec.and(bookDtoSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(bookSearchParametersDto.author()));
        }

        if (bookSearchParametersDto.title() != null) {
            spec = spec.and(bookDtoSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(bookSearchParametersDto.title()));
        }

        return spec;
    }
}
