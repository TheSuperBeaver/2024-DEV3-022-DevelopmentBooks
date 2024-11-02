package be.bnppf.development.books.application.controller;

import be.bnppf.development.books.application.dto.Basket;
import be.bnppf.development.books.domain.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
@Tag(name = "Basket controller", description = "Calculation of a basket total value")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/total")
    @Operation(summary = "Calculate a basket total value", description = "Will take into account the discount when you choose multiple books in the series")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total is calculated"),
            @ApiResponse(responseCode = "404", description = "One of the books is not found")
    })
    public double calculateTotal(@RequestBody Basket basket) {
        return basketService.calculateTotal(basket);
    }
}