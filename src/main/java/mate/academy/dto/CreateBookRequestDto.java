package mate.academy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.Set;

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
    private Set<CreateCategoryRequestDto> categories;

    // правильно виставити в маппері, бо мапиться як null

    private String description;
    private String coverImage;
}
