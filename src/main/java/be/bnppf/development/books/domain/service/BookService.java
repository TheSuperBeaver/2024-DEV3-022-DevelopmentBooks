package be.bnppf.development.books.domain.service;

import be.bnppf.development.books.domain.model.Book;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = List.of(
            new Book(1, "Clean Code", "Robert Martin", 2008),
            new Book(2, "The Clean Coder", "Robert Martin", 2011),
            new Book(3, "Clean Architecture", "Robert Martin", 2017),
            new Book(4, "Test Driven Development by Example", "Kent Beck", 2003),
            new Book(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004)
    );

    public List<Book> getAllBooks() {
        return books;
    }
}
