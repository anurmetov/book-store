package mate.academy.repository.providers;

import mate.academy.dto.BookDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<BookDto> {
    private static final String AUTHOR_COLUMN = "author";

    @Override
    public String getKey() {
        return AUTHOR_COLUMN;
    }

    public Specification<BookDto> getSpecification(String param) {
        return (root, query, criteriaBuilder)
                -> root.get(AUTHOR_COLUMN).in(param);
    }
}
