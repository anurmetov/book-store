package mate.academy.repository.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.exception.DataProcessingException;
import mate.academy.model.Book;
import mate.academy.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.persist(book);

            transaction.commit();

            return book;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not save book", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Book", Book.class)
                    .getResultList();

        } catch (Exception e) {
            throw new DataProcessingException("Could not fetch books", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> query =
                    session.createQuery("FROM Book WHERE id = :id", Book.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Could not fetch books", e);
        }
    }
}
