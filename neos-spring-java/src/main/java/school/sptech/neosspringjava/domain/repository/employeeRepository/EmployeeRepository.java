package school.sptech.neosspringjava.domain.repository.employeeRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    Employee findByEmailAndPassaword(String email, String password);


    Employee findByEmailAndPassaword(String email, String password);
}
