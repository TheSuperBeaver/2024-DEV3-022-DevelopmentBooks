package be.bnppf.development.books;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevelopmentBooksApplicationTests {

    @Test
    void mainMethodRuns() {
        // Running the main method directly to achieve coverage
        DevelopmentBooksApplication.main(new String[]{});
    }
}
