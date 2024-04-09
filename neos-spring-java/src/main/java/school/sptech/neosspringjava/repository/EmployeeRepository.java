package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{


    public Employee findByEmailAndPassword(String email, String password);

   



}
