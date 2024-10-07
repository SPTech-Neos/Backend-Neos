package school.sptech.neosspringjava.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.EmployeeServices;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Service;

public interface EmployeeServicesRepository extends JpaRepository<EmployeeServices, Integer>{
    List<EmployeeServices> findAllByEmployee(Employee employeeId);


    @Query("SELECT e FROM EmployeeServices es JOIN es.employee e WHERE e.establishment = :idEstab AND es.service = :idServ")
    List<Employee> findAllByEstablishmentAndService(@Param("idEstab") Establishment estab, @Param("idServ") Service serv);
    @Query("SELECT s FROM Service s JOIN EmployeeServices es ON s.id = es.service.id " +
    "JOIN Employee e ON es.employee.id = e.id " +
    "WHERE e.establishment.id = :id")
    List<Service> findServicesById(@Param("id") Integer id);
}
