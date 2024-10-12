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


    @Query("SELECT e FROM EmployeeServices es JOIN es.employee e WHERE e.establishment = :idEstab AND es.service = :idServ")
    List<Employee> findAllByEstablishmentAndService(@Param("idEstab") Establishment estab, @Param("idServ") Service serv);
    @Query("SELECT s FROM Service s JOIN EmployeeServices es ON s.id = es.service.id " +
    "JOIN Employee e ON es.employee.id = e.id " +
    "WHERE e.establishment.id = :id")
    List<Service> findServicesById(@Param("id") Integer id);

        @Query("SELECT s FROM EmployeeServices es " +
        "JOIN es.employee e " +
        "JOIN es.service s " +
        "JOIN s.status st " +
        "WHERE e.establishment.id = :establishmentId")
    List<Service> findServicesByEstablishmentId(@Param("establishmentId") Integer establishmentId);

        @Query("SELECT s FROM EmployeeServices es " +
        "JOIN es.employee e " +
        "JOIN es.service s " +
        "JOIN s.status st " +
        "WHERE e.establishment.id = :establishmentId " +
        "AND s.status.id = :statusId")
    List<Service> findServicesByEstablishmentAndStatus(@Param("establishmentId") Integer establishmentId, @Param("statusId") Integer statusId);

        @Query("SELECT s FROM EmployeeServices es " +
        "JOIN es.employee e " +
        "JOIN es.service s " +
        "JOIN s.status st " +
        "WHERE e.id = :employeeId")
    List<Service> findServicesByEmployeeId(@Param("employeeId") Integer employeeId);

        @Query("SELECT s FROM EmployeeServices es " +
        "JOIN es.employee e " +
        "JOIN es.service s " +
        "JOIN s.serviceType st " +
        "JOIN st.serviceCategory sc " +
        "WHERE e.id = :employeeId AND sc.id = :serviceCategoryId")
    List<Service> findServicesByEmployeeIdAndServiceCategory(@Param("employeeId") Integer employeeId, @Param("serviceCategoryId") Integer serviceCategoryId);



}
