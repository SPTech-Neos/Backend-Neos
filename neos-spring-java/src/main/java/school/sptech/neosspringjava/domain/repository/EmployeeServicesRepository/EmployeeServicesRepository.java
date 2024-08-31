package school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeServices.EmployeeServices;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.service.Service;

public interface EmployeeServicesRepository extends JpaRepository<EmployeeServices, Integer>{
    List<EmployeeServices> findAllByEmployee(Employee employeeId);

}
