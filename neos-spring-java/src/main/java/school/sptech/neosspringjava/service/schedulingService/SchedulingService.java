package school.sptech.neosspringjava.service.schedulingService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.scheduligDto.ScheduligResponse;
import school.sptech.neosspringjava.api.dtos.schedulingStatusDto.schedulingStatusRequest;
import school.sptech.neosspringjava.api.mappers.scheduligMapper.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.schedulingStatus.schedulingStatus;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.schedulingStatusRepository.SchedulingStatusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
