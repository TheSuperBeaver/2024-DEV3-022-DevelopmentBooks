package be.bnppf.development.books.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {

    @NotBlank
    @Size(min = 1, max = 5)
    int bookId;

    @NotBlank
    int quantity;
}
