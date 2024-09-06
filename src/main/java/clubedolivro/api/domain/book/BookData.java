package clubedolivro.api.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookData(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String synopsis,
        @NotBlank
        String releaseDate,
        @NotNull
        Genre genre,
        @NotBlank
        String price) {
}
