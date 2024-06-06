package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

public record RatingResponse(Integer id,
Integer nota,
Establishment establishment,
Client client) {

}
