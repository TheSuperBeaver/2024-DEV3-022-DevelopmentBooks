package be.bnppf.development.books.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static be.bnppf.development.books.domain.model.BookMother.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BookTest {

    @Test
    void verifyEqualValues() {
        Book book1 = cleanCodeBook();
        Book book2 = cleanCodeBook();

        assertEquals(book1, book2);
        assertEquals(book1, book1);
        assertEquals(book1.hashCode(), book2.hashCode());
        assertEquals(book1.hashCode(), book1.hashCode());
    }

    static Stream<Arguments> allUnequalValues() {
        return Stream.of(
                arguments(theCleanCoderBook(), null),
                arguments(cleanCodeBook(), workingEffectivelyWithLegacyCode()),
                arguments(cleanCodeBook(), "Different class"),
                arguments(theCleanCoderBook(), new Book(2, "Same id", "Other", 2012, 51)),
                arguments(theCleanCoderBook(), new Book(2, "Same id and year", "Other", 2011, 51)),
                arguments(theCleanCoderBook(), new Book(2, "Same id, year and price", "Other", 2011, 50)),
                arguments(theCleanCoderBook(), new Book(2, "The Clean Coder", "Other", 2011, 50))
        );
    }

    /**
     * A parameterized test to cover all possible values in the equals method.
     * All the values found in the method source above will be tested to not be equals to each other.
     * @param firstValue : A book
     * @param otherValue : A different object
     */
    @ParameterizedTest
    @MethodSource("allUnequalValues")
    void verifyAllUnequalValues(Book firstValue, Object otherValue) {
        assertNotEquals(firstValue, otherValue);
        if (firstValue != null && otherValue != null) {
            assertNotEquals(firstValue.hashCode(), otherValue.hashCode());
        }
    }

    @Test
    void canInstantiateWithoutPrice() {
        Book book = new Book(1, "title", "author", 2024);
        assertEquals(1, book.getId());
        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals(2024, book.getYear());
        assertEquals(50, book.getPrice());
        assertEquals("Book{id=1, title='title', author='author', year=2024, price=50.0}", book.toString());
    }

    @Test
    void canSetValues() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setYear(2024);
        book.setPrice(50);

        assertEquals(0, book.getId());
        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals(2024, book.getYear());
        assertEquals(50, book.getPrice());
        assertEquals("Book{id=0, title='title', author='author', year=2024, price=50.0}", book.toString());
    }

}