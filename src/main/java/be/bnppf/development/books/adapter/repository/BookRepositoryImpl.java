package be.bnppf.development.books.adapter.repository;

import be.bnppf.development.books.domain.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private static final List<Book> BOOKS = List.of(
            new Book(1, "Clean Code", "Robert Martin", 2008),
            new Book(2, "The Clean Coder", "Robert Martin", 2011),
            new Book(3, "Clean Architecture", "Robert Martin", 2017),
            new Book(4, "Test Driven Development by Example", "Kent Beck", 2003),
            new Book(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004)
    );
    @Override
    public Optional<Book> findById(int bookId) {
        return BOOKS.stream().filter(book -> book.getId() == bookId).findFirst();
    }

    @Override
    public List<Book> findAll() {
        return BOOKS;
    }
}
