package school.sptech.neosspringjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
