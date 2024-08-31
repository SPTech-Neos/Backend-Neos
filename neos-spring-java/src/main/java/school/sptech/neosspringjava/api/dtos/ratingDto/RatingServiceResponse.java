package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.service.Service;

public record RatingServiceResponse(
        Integer id,
        Integer avaliation,
        Service service,
        Client client
) {
}
