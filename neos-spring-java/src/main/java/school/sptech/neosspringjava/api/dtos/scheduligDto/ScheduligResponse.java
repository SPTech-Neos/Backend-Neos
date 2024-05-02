package school.sptech.neosspringjava.api.dtos.scheduligDto;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.service.Service;

public record ScheduligResponse(Integer idSchedulig, Client client,  Service service, Employee employee) {
   
   
}
