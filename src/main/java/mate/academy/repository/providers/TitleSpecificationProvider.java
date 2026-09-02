package mate.academy.repository.providers;

import mate.academy.dto.BookDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<BookDto> {
    private static final String TITLE_COLUMN = "title";

    @Override
    public String getKey() {
        return TITLE_COLUMN;
    }

    public Specification<BookDto> getSpecification(String param) {
        return (root, query, criteriaBuilder)
                -> root.get(TITLE_COLUMN).in(param);
    }
}
