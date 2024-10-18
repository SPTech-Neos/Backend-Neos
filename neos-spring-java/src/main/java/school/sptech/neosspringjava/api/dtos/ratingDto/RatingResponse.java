package school.sptech.neosspringjava.api.dtos.ratingDto;

import io.jsonwebtoken.impl.lang.Services;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.Service;

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
