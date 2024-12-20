package school.sptech.neosspringjava.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.api.dtos.dashboardDto.EmployeeStats;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Service;
import school.sptech.neosspringjava.domain.model.Status;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmailAndPassword(String email, String password);

    List<Employee> findAllByEstablishment(Establishment establishment);

    Optional<Employee> findByEmail(String email);
    Boolean existsByEmail(String email);

    List<Employee> findAllByStatus(Status status);

    @Query("SELECT e FROM EmployeeServices es JOIN es.employee e WHERE e.establishment = :idEstab AND es.service = :idServ")
    List<Employee> findAllByEstablishmentAndService(@Param("idEstab") Establishment estab, @Param("idServ") Service serv);

    List<Employee> findAllByEstablishmentId(Integer establishmentId);

    @Query("SELECT s FROM Service s JOIN EmployeeServices es ON s.id = es.service.id " +
    "JOIN Employee e ON es.employee.id = e.id " +
    "WHERE e.establishment.id = :id")
    List<Service> findServicesById(@Param("id") Integer id);

}