package mate.academy.dto;

public record OrderItemResponseDto(
        Long id,
        Long bookId,
        int quantity
) {
}
