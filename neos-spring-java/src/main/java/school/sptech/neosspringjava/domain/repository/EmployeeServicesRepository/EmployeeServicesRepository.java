package school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;

public interface EmployeeServicesRepository extends JpaRepository<EmployeeServices, Integer>{


    List<EmployeeServices> findAllByEmployee(Employee employeeId);

}
