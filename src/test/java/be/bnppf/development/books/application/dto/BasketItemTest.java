package be.bnppf.development.books.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BasketItemTest {

    @Test
    void testGettersAndSetters() {
        BasketItem item = new BasketItem(1, 3);

        assertEquals(1, item.getBookId());
        assertEquals(3, item.getQuantity());
    }

    @Test
    void testEqualsAndHashCode() {
        BasketItem item1 = new BasketItem(1, 3);
        BasketItem item2 = new BasketItem(1, 3);

        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());

        item2.setQuantity(5);
        assertNotEquals(item1, item2);
        assertNotEquals(item1.hashCode(), item2.hashCode());
    }

    @Test
    void testToString() {
        BasketItem item1 = new BasketItem(1, 3);

        String expectedString = "BasketItem(bookId=1, quantity=3)";
        assertEquals(expectedString, item1.toString());
    }
}
