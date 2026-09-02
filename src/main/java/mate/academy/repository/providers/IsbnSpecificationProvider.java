package mate.academy.repository.providers;

import mate.academy.dto.BookDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<BookDto> {
    private static final String ISBN_COLUMN = "isbn";

    @Override
    public String getKey() {
        return ISBN_COLUMN;
    }

    public Specification<BookDto> getSpecification(String param) {
        return (root, query, criteriaBuilder)
                -> root.get(ISBN_COLUMN).in(param);
    }
}
