package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Set;
import lombok.Data;
import mate.academy.model.Category;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @Positive
    @NotNull
    private double price;

    @NotNull
    private Set<Category> categories;

    private String description;
    private String coverImage;
}
