package be.bnppf.development.books.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void equalsAndHashCode_WhenTwoBooksAreEqual() {
        Book book1 = BookMother.cleanCodeBook();
        Book book2 = BookMother.cleanCodeBook();

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void equalsAndHashCode_WhenTwoBooksAreDifferent() {
        Book book1 = BookMother.cleanCodeBook();
        Book book2 = BookMother.workingEffectivelyWithLegacyCode();

        assertNotEquals(book1, book2);
        assertNotEquals(book1.hashCode(), book2.hashCode());
    }

}