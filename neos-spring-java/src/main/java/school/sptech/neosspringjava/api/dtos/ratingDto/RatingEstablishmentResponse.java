package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.Service;

public record RatingEstablishmentResponse(
        Integer id,
        Integer avaliation,
        Establishment establishment,
        Client client
) {
}
