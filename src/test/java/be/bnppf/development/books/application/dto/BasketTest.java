package be.bnppf.development.books.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasketTest {

    private Basket basket1;
    private Basket basket2;
    private BasketItem item1;
    private BasketItem item2;

    @BeforeEach
    void setUp() {
        item1 = new BasketItem(1, 2);
        item2 = new BasketItem(2, 5);

        basket1 = new Basket(List.of(item1, item2));
        basket2 = new Basket(List.of(item1, item2));
    }

    @Test
    void testGetItems() {
        assertEquals(2, basket1.getItems().size());
        assertTrue(basket1.getItems().contains(item1));
        assertTrue(basket1.getItems().contains(item2));
    }

    @Test
    void testSetItems() {
        BasketItem newItem = new BasketItem(3, 1);

        basket1.setItems(new ArrayList<>(List.of(newItem)));

        assertEquals(1, basket1.getItems().size());
        assertTrue(basket1.getItems().contains(newItem));
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(basket1, basket2);
        assertEquals(basket1.hashCode(), basket2.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Basket(items=[BasketItem(bookId=1, quantity=2), BasketItem(bookId=2, quantity=5)])";
        assertEquals(expectedString, basket1.toString());
    }
}
