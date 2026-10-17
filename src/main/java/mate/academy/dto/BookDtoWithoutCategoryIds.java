package mate.academy.dto;

public record BookDtoWithoutCategoryIds(
        Long id,
        String title,
        String author,
        String isbn,
        double price,
        String description,
        String coverImage
) {
}
