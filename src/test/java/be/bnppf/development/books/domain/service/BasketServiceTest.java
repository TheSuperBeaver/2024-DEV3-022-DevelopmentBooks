package be.bnppf.development.books.domain.service;

import be.bnppf.development.books.adapter.repository.BookRepository;
import be.bnppf.development.books.application.dto.Basket;
import be.bnppf.development.books.application.dto.BasketItem;
import be.bnppf.development.books.domain.model.BookMother;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock(strictness = Mock.Strictness.LENIENT)
    private BookRepository bookRepository;

    @InjectMocks
    private BasketService basketService;

    @ParameterizedTest
    @MethodSource("provideBasketsForTotalCalculation")
    void calculateTotal_ShouldReturnExpectedTotal(Basket basket, double expectedTotal) {
        when(bookRepository.findById(1)).thenReturn(Optional.of(BookMother.cleanCodeBook()));
        when(bookRepository.findById(2)).thenReturn(Optional.of(BookMother.theCleanCoderBook()));
        when(bookRepository.findById(3)).thenReturn(Optional.of(BookMother.cleanArchitectureBook()));
        when(bookRepository.findById(4)).thenReturn(Optional.of(BookMother.testDrivenDevelopmentByExample()));
        when(bookRepository.findById(5)).thenReturn(Optional.of(BookMother.workingEffectivelyWithLegacyCode()));

        double actualTotal = basketService.calculateTotal(basket);

        assertEquals(expectedTotal, actualTotal);
    }

    static Stream<Arguments> provideBasketsForTotalCalculation() {
        return Stream.of(
                arguments(new Basket(of(new BasketItem(1, 1))), 50.0),
                arguments(new Basket(of(new BasketItem(1, 2))), 100), // No discount, 2 books
                arguments(new Basket(of(new BasketItem(2, 1), new BasketItem(1, 2))), 145.0), // 5% discount, 1 set of 2 books, and 1 book at full price
                arguments(new Basket(of(new BasketItem(1, 2), new BasketItem(2, 2))), 190.0), // 2*5% discount
                arguments(new Basket(of(new BasketItem(1, 1), new BasketItem(2, 1), new BasketItem(3, 1))), 135.0), // 10% discount 3 different books
                arguments(new Basket(of(new BasketItem(1, 1), new BasketItem(2, 1), new BasketItem(3, 1), new BasketItem(4, 1))), 160.0), // 20% discount 4 different books
                arguments(new Basket(of(new BasketItem(1, 1), new BasketItem(2, 1), new BasketItem(3, 1), new BasketItem(4, 1), new BasketItem(5, 1))), 210.0), // 25% discount max for 5+ books
                arguments(new Basket(of(new BasketItem(1, 2), new BasketItem(2, 2), new BasketItem(3, 2), new BasketItem(4, 1), new BasketItem(5, 1))), 320.0) // Kata example
        );
    }

}
