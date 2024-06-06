package school.sptech.neosspringjava.api.dtos.scheduligDto;

import java.time.LocalDateTime;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.service.Service;

@Builder
public record ScheduligRequest( LocalDateTime dateTime,Integer idScheduligStatus, Integer idService, Integer idEmployee, Integer idClient) {
}
