package be.bnppf.development.books.application.exception;

import be.bnppf.development.books.domain.service.exceptions.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponseException> handleBookNotFoundException(BookNotFoundException ex) {
        ErrorResponseException errorResponseException = new ErrorResponseException(
                ex.getMessage(),
                "The book you are looking for does not exist in our records."
        );
        return new ResponseEntity<>(errorResponseException, HttpStatus.NOT_FOUND);
    }
}
