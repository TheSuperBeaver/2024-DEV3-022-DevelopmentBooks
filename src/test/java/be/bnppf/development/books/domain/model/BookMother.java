package be.bnppf.development.books.domain.model;

public class BookMother {

    public static Book cleanCodeBook() {
        return new Book(1, "Clean Code", "Robert Martin", 2008, 50);
    }

    public static Book theCleanCoderBook() {
        return new Book(2, "The Clean Coder", "Robert Martin", 2011, 50);
    }

    public static Book cleanArchitectureBook() {
        return new Book(3, "Clean Architecture", "Robert Martin", 2017, 50);
    }

    public static Book testDrivenDevelopmentByExample() {
        return new Book(4, "Test Driven Development by Example", "Kent Beck", 2003, 50);
    }

    public static Book workingEffectivelyWithLegacyCode() {
        return new Book(5, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50);
    }

}
