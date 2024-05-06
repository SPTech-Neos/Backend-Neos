package school.sptech.neosspringjava.domain.repository.schedulingRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Integer>{

List<Scheduling> findByEmployeeAndDateTimeBetween(Employee employee, LocalDateTime start, LocalDateTime end);


}
