package school.sptech.neosspringjava.api.dtos.orderDto;

import lombok.Builder;
import lombok.Data;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(
        Integer id,
        LocalDateTime dateTime,
        Status status,
        Client client,
        Employee employee

) {
}
