package school.sptech.neosspringjava.api.dtos.scheduligDto;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.service.Service;

@Builder
public record ScheduligRequest( Integer idClient, Integer idService, Integer idEmployee) {
}
