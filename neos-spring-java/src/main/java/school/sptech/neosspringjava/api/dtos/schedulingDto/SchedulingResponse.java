package school.sptech.neosspringjava.api.dtos.schedulingDto;

import java.time.LocalDateTime;

import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Service;
import school.sptech.neosspringjava.domain.model.Status;

public record SchedulingResponse(Integer idSchedulig, Client client, Service service, Employee employee, Status status, LocalDateTime dateTime) {
   
   
}
