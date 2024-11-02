package be.bnppf.development.books.application.controller;

import be.bnppf.development.books.application.dto.Basket;
import be.bnppf.development.books.application.dto.BasketItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@WebFluxTest(controllers = {BasketController.class})
class BasketControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void postABasket_shouldCalculateThePriceCorrectly() {

        Basket basket = new Basket(List.of(
                new BasketItem(1, 2),
                new BasketItem(2, 1)
        ));

        webTestClient.post().uri("/api/basket/total")
                .contentType(APPLICATION_JSON)
                .body(fromValue(basket))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(Double.class)
                .isEqualTo(145.0);
    }

    @Test
    void postABasket_withIncorrectBooks_shouldReturn404_andProveGlobalExceptionHandlerWorks() {

        Basket basket = new Basket(List.of(
                new BasketItem(6, 2),
                new BasketItem(2, 1)
        ));

        webTestClient.post().uri("/api/basket/total")
                .contentType(APPLICATION_JSON)
                .body(fromValue(basket))
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(String.class)
                .isEqualTo("{\"message\":\"Book with id [6] not found\",\"details\":\"The book you are looking for does not exist in our records.\"}");
    }

}