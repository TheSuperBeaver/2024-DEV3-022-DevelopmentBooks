package be.bnppf.development.books.adapter.repository;

import be.bnppf.development.books.domain.model.Book;
import be.bnppf.development.books.domain.model.BookMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

@SpringBootTest
class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAll_ShouldReturnAllBooks() {
        List<Book> books = bookRepository.findAll();

        assertThat(books).hasSize(5)
                .contains(BookMother.cleanCodeBook(),
                        BookMother.theCleanCoderBook(),
                        BookMother.cleanArchitectureBook(),
                        BookMother.testDrivenDevelopmentByExample(),
                        BookMother.workingEffectivelyWithLegacyCode()
                );
    }

    @ParameterizedTest
    @MethodSource("allTestCases")
    void findById_ShouldReturnExpectedBook(int bookId, Optional<Book> expectedBook) {
        Optional<Book> result = bookRepository.findById(bookId);

        assertThat(result).isEqualTo(expectedBook);
    }

    private static Stream<Arguments> allTestCases() {
        return Stream.of(
                of(1, Optional.of(BookMother.cleanCodeBook())),
                of(2, Optional.of(BookMother.theCleanCoderBook())),
                of(3, Optional.of(BookMother.cleanArchitectureBook())),
                of(4, Optional.of(BookMother.testDrivenDevelopmentByExample())),
                of(5, Optional.of(BookMother.workingEffectivelyWithLegacyCode())),
                of(6, Optional.empty())
        );
    }
}
