package school.sptech.neosspringjava.api.dtos.ratingDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.product.Product;

public record RatingEmployeeResponse(
        Integer id,
        Integer avaliation,
        Employee employee,
        Client client
) {
}
