package be.bnppf.development.books.domain.service;

import be.bnppf.development.books.adapter.repository.BookRepository;
import be.bnppf.development.books.application.dto.Basket;
import be.bnppf.development.books.application.dto.BasketItem;
import be.bnppf.development.books.domain.model.Book;
import be.bnppf.development.books.domain.model.BookDiscountRate;
import be.bnppf.development.books.domain.service.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final BookRepository bookRepository;

    @Autowired
    public BasketService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public double calculateTotal(Basket basket) {
        Map<Integer, Integer> bookQuantities = basket.getItems().stream().
                collect(Collectors.toMap(BasketItem::getBookId, BasketItem::getQuantity, Integer::sum));

        return calculateOptimalPrice(bookQuantities);
    }

    private double calculateOptimalPrice(Map<Integer, Integer> bookQuantities) {
        List<Double> possiblePrices = calculatePricesForGrouping(bookQuantities);

        // Return the minimum price from possible groupings
        return possiblePrices.stream().min(Double::compare).orElse(0.0);
    }

    private List<Double> calculatePricesForGrouping(Map<Integer, Integer> bookQuantities) {
        List<Double> possiblePrices = new ArrayList<>();
        for (int i = 4; i < 5; i++) {
            possiblePrices.add(calculatePriceForGrouping(bookQuantities, i));
        }
        return possiblePrices;
    }

    private double calculatePriceForGrouping(Map<Integer, Integer> bookQuantities, int maxGroupSize) {
        Map<Integer, Integer> remainingQuantities = new HashMap<>(bookQuantities);
        double total = 0.0;

        while (remainingQuantities.values().stream().anyMatch(quantity -> quantity > 0)) {
            Set<Integer> currentSet = createGroupSet(remainingQuantities, maxGroupSize);
            total += calculateGroupPrice(currentSet);
        }

        return total;
    }

    private Set<Integer> createGroupSet(Map<Integer, Integer> bookQuantities, int maxGroupSize) {
        Set<Integer> currentSet = bookQuantities.keySet().stream()
                .filter(bookId -> bookQuantities.get(bookId) > 0)
                .limit(maxGroupSize)
                .collect(Collectors.toSet());

        currentSet.forEach(bookId -> bookQuantities.put(bookId, bookQuantities.get(bookId) - 1));

        return currentSet;
    }

    private double calculateGroupPrice(Set<Integer> bookSet) {
        double groupPrice = bookSet.stream()
                .mapToDouble(bookId -> bookRepository.findById(bookId)
                        .map(Book::getPrice)
                        .orElseThrow(() -> new BookNotFoundException(bookId)))
                .sum();

        double discountRate = BookDiscountRate.getDiscountRateForUniqueCount(bookSet.size());

        return groupPrice * (1 - discountRate);
    }
}