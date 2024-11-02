package be.bnppf.development.books.domain.service;

import be.bnppf.development.books.adapter.repository.BookRepository;
import be.bnppf.development.books.domain.model.Book;
import be.bnppf.development.books.domain.model.BookMother;
import be.bnppf.development.books.domain.service.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        List<Book> mockBooks = List.of(
                BookMother.cleanCodeBook(),
                BookMother.cleanArchitectureBook()
        );
        when(bookRepository.findAll()).thenReturn(mockBooks);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookWithId_ShouldReturnBook_WhenBookExists() {
        int bookId = 1;
        Book mockBook = BookMother.cleanCodeBook();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));

        Book result = bookService.getBookWithId(bookId);

        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
        assertEquals(2008, result.getYear());
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void getBookWithId_ShouldThrowBookNotFoundException_WhenBookDoesNotExist() {
        int bookId = 99;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookNotFoundException exception = assertThrows(
                BookNotFoundException.class,
                () -> bookService.getBookWithId(bookId)
        );
        assertEquals("Book with id [" + bookId + "] not found", exception.getMessage());
        verify(bookRepository, times(1)).findById(bookId);
    }
}
