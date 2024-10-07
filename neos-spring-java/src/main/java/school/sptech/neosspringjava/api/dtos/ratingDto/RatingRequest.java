package school.sptech.neosspringjava.api.dtos.ratingDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Establishment;
@Builder
public record RatingRequest(
    Integer avaliation,
    Integer establishment,
    Integer client,

    Integer employee,

    Integer service,

    Integer product
) {

}
