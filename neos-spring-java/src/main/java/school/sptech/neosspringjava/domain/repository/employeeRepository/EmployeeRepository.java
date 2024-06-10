package school.sptech.neosspringjava.domain.repository.employeeRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    Employee findByEmailAndPassword(String email, String password);

    List<Employee> findByEstablishment(Establishment establishment);

    List<Employee> findAllByEstablishment(Establishment establishment);

    Optional<Employee> findByEmail(String email);

}
