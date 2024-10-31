package be.bnppf.development.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class DevelopmentBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelopmentBooksApplication.class, args);
	}

}