package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Service;

public record RatingServiceResponse(
        Integer id,
        Integer avaliation,
        Service service,
        Client client
) {
}
