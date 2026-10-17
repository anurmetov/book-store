package mate.academy.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import mate.academy.model.Category;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private Set<Category> categories;
    private String description;
    private String coverImage;
}
