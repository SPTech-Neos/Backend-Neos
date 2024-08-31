package school.sptech.neosspringjava.api.dtos.ratingDto;

import jakarta.validation.constraints.NotNull;

public record RatingServiceRequest(
        @NotNull
        Integer avaliation,
        @NotNull
        Integer service,
        @NotNull
        Integer client
) {
}
