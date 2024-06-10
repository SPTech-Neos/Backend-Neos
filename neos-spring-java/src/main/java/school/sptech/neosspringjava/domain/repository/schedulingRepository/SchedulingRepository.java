package school.sptech.neosspringjava.domain.repository.schedulingRepository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Integer>{

List<Scheduling> findByEmployeeAndDateTimeBetween(Integer integer, LocalDateTime start, LocalDateTime end);

    @Query("SELECT s FROM Scheduling s WHERE s.employee = :employee AND s.dateTime BETWEEN :startDate AND :endDate")
    List<Scheduling> findByEmployeeAndDateRange(@Param("employee") Employee employee, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
