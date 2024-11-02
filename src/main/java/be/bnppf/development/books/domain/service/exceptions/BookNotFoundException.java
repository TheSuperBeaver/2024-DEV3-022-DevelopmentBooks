package be.bnppf.development.books.domain.service.exceptions;

import java.text.MessageFormat;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Integer bookId) {
        super(MessageFormat.format("Book with id [{0}] not found", bookId));
    }
}
