package be.bnppf.development.books.adapter.repository;

import be.bnppf.development.books.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(int bookId);
    List<Book> findAll();

}
