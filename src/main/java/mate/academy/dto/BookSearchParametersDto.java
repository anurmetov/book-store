package mate.academy.dto;

import jakarta.validation.constraints.NotNull;

public record BookSearchParametersDto(
        @NotNull
        String title,
        @NotNull
        String author,
        @NotNull
        String isbn) {
}
