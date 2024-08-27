package school.sptech.neosspringjava.service.schedulingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.schedulingDto.ScheduligResponse;
import school.sptech.neosspringjava.api.mappers.schedulingMapper.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;

    public List<ScheduligResponse> getSchedulesByClientId(Integer clientId) {
        List<Scheduling> schedules = schedulingRepository.findByClientId(clientId);
        List<ScheduligResponse> schedulings = new ArrayList<>();

        for (Scheduling schedule : schedules) {
            schedulings.add(ScheduligMapper.toScheduligResponse(schedule));
        }

        return schedulings;
    }

    public List<ScheduligResponse> getSchedulesByEmployeeId(Integer employeeId) {
        List<Scheduling> schedules = schedulingRepository.findByEmployeeId(employeeId);
        List<ScheduligResponse> schedulings = new ArrayList<>();

        for (Scheduling schedule : schedules) {
            schedulings.add(ScheduligMapper.toScheduligResponse(schedule));
        }

        return schedulings;
    }

}
