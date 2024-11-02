package be.bnppf.development.books.application.controller;

import be.bnppf.development.books.domain.model.Book;
import be.bnppf.development.books.domain.model.BookMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@WebFluxTest(controllers = {BookController.class})
public class BookControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        webTestClient.get().uri("/api/books")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBodyList(Book.class)
                .hasSize(5)
                .contains(
                        BookMother.cleanCodeBook(),
                        BookMother.theCleanCoderBook(),
                        BookMother.cleanArchitectureBook(),
                        BookMother.testDrivenDevelopmentByExample(),
                        BookMother.workingEffectivelyWithLegacyCode()
                );
    }
}
