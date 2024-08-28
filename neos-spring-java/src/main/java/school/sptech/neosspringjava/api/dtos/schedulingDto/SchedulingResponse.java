package school.sptech.neosspringjava.api.dtos.schedulingDto;

import java.time.LocalDateTime;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.status.Status;

public record SchedulingResponse(Integer idSchedulig, Client client, Service service, Employee employee, Status status, LocalDateTime dateTime) {
   
   
}
