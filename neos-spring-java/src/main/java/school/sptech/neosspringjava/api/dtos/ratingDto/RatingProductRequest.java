package school.sptech.neosspringjava.api.dtos.ratingDto;

import jakarta.validation.constraints.NotNull;

public record RatingProductRequest(
        @NotNull
        Integer avaliation,
        @NotNull
        Integer product,
        @NotNull
        Integer client
) {
}
