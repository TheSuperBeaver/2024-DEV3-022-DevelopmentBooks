package be.bnppf.development.books.application.controller;

import be.bnppf.development.books.domain.model.Book;
import be.bnppf.development.books.domain.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book controller", description = "Retrieve all the books in the store")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get a list of all the books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "List of books in the store")
    })
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}
