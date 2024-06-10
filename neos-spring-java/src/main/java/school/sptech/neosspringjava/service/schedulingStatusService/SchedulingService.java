package school.sptech.neosspringjava.service.schedulingStatusService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.scheduligDto.ScheduligResponse;
import school.sptech.neosspringjava.api.mappers.scheduligMapper.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;
@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingStatusService schedulingStatusService;
    private final ScheduligMapper scheduligMapper;
    private final SchedulingRepository schedulingRepository;

    public List<ScheduligResponse> findCancellationByEmployee(Integer integer, LocalDateTime initialDate) {

        List<Scheduling> scheduling = schedulingRepository.findByEmployeeAndDateTimeBetween(integer, initialDate, LocalDateTime.now());

        List<ScheduligResponse> scheduligResponse = new ArrayList<>();

        for (Scheduling scheduling2 : scheduling) {
            scheduligResponse.add(scheduligMapper.toScheduligResponse(scheduling2));
        }

        return scheduligResponse;
    }

}
