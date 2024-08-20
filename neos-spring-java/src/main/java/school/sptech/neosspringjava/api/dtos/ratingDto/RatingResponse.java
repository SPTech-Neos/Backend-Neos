package school.sptech.neosspringjava.api.dtos.ratingDto;

import io.jsonwebtoken.impl.lang.Services;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.service.Service;

public record RatingResponse(
        Integer id,
        Integer nota,
        Establishment establishment,
        Client client,

        Employee employee,

        Service service,

        Product product
) {
}
