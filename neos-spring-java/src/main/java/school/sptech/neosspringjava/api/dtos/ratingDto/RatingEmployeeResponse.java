package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Product;

public record RatingEmployeeResponse(
        Integer id,
        Integer avaliation,
        Employee employee,
        Client client
) {
}
