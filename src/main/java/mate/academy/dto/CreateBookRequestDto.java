package mate.academy.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String isbn;

    @Min(0)
    private double price;

    private String description;
    private String coverImage;
}
