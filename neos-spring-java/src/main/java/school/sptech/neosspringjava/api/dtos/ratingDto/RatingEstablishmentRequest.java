package school.sptech.neosspringjava.api.dtos.ratingDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RatingEstablishmentRequest(
        @NotNull
        Integer avaliation,
        @NotNull
        Integer establishment,
        @NotNull
        Integer client
) {
}
