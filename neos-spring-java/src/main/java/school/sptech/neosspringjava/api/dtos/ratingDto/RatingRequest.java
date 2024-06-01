package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

public record RatingRequest(
Integer nota,
Integer establishment,
Integer client) {

}
