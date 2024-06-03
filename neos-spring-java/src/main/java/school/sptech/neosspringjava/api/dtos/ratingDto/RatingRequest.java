package school.sptech.neosspringjava.api.dtos.ratingDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
@Builder
public record RatingRequest(
Integer nota,
Integer establishment,
Integer client) {

}
