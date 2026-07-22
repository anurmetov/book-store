package mate.academy.dto;

import lombok.Data;

@Data
public class CreateBookRequestDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private String description;
    private String coverImage;
}
