package be.bnppf.development.books.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseException {

    private String message;
    private String details;
}
