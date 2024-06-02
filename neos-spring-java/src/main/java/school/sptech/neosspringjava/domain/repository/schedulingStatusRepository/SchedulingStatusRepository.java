package school.sptech.neosspringjava.domain.repository.schedulingStatusRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import school.sptech.neosspringjava.domain.model.schedulingStatus.schedulingStatus;

public interface SchedulingStatusRepository extends JpaRepository<schedulingStatus, Integer>{

}
