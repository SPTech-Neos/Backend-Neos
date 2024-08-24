package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.service.Service;

public record RatingEstablishmentResponse(
        Integer id,
        Integer avaliation,
        Establishment establishment,
        Client client
) {
}
