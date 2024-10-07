package school.sptech.neosspringjava.domain.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Integer>{

    List<Scheduling> findByEmployeeAndDateTimeScheduleBetween(Employee employee, LocalDateTime start, LocalDateTime end);

    @Query("SELECT s FROM Scheduling s WHERE s.employee = :employee AND s.dateTimeSchedule BETWEEN :startDate AND :endDate")
    List<Scheduling> findByEmployeeAndDateRange(@Param("employee") Employee employee, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Scheduling> findByClientId(Integer clientId);

    List<Scheduling> findByEmployeeId(Integer employeeId);
    
    @Query("SELECT e.imgUrl AS Foto, " +
           "e.name AS Nome, " +
           "COALESCE(ROUND(AVG(r.avaliation), 1), 0) AS Media_Avaliacao, " +
           "COALESCE(SUM(es.hoursSpent), 0) AS Total_Horas_Agendadas, " +
           "COALESCE(SUM(s.price), 0) AS Total_Valor " +
           "FROM Scheduling sc " +
           "JOIN sc.employee e " +
           "JOIN sc.service s " +
           "LEFT JOIN Rating r ON e.id = r.employee.id " +
           "LEFT JOIN EmployeeServices es ON e.id = es.employee.id " +
           "WHERE e.establishment.id = :establishmentId " +
           "AND sc.dateTimeSchedule BETWEEN :startDate AND :endDate " +
           "GROUP BY e.id " +
           "ORDER BY Total_Horas_Agendadas DESC")
    List<Object[]> findEmployeeStats(
            @Param("establishmentId") Integer establishmentId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
