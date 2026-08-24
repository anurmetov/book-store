package mate.academy.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.model.Book;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Book> findAll(BookSearchParametersDto bookSearchParametersDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicateList = new ArrayList<>();

        if (bookSearchParametersDto.title() != null
                && !bookSearchParametersDto.title().isEmpty()) {
            predicateList.add(
                    cb.like(
                            book.get("title"),
                            "%" + bookSearchParametersDto.title() + "%"
                    )
            );
        }

        if (bookSearchParametersDto.author() != null
                && !bookSearchParametersDto.author().isEmpty()) {
            predicateList.add(
                    cb.like(
                            book.get("author"),
                            "%" + bookSearchParametersDto.author() + "%"
                    )
            );
        }

        if (bookSearchParametersDto.isbn() != null
                && !bookSearchParametersDto.isbn().isEmpty()) {
            predicateList.add(
                    cb.like(
                            book.get("isbn"),
                            "%" + bookSearchParametersDto.isbn() + "%"
                    )
            );
        }

        if (predicateList.isEmpty()) {
            return List.of();
        }

        cq.where(predicateList.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}

