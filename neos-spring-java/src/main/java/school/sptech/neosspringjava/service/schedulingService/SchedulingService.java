package school.sptech.neosspringjava.service.schedulingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.schedulingDto.SchedulingResponse;
import school.sptech.neosspringjava.api.mappers.schedulingMapper.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;

    public Scheduling findById(Integer id){
        return schedulingRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }
    public List<SchedulingResponse> getSchedulesByClientId(Integer clientId) {
        List<Scheduling> schedules = schedulingRepository.findByClientId(clientId);
        List<SchedulingResponse> schedulings = new ArrayList<>();

        for (Scheduling schedule : schedules) {
            schedulings.add(ScheduligMapper.toScheduligResponse(schedule));
        }

        return schedulings;
    }

    public List<SchedulingResponse> getSchedulesByEmployeeId(Integer employeeId) {
        List<Scheduling> schedules = schedulingRepository.findByEmployeeId(employeeId);
        List<SchedulingResponse> schedulings = new ArrayList<>();

        for (Scheduling schedule : schedules) {
            schedulings.add(ScheduligMapper.toScheduligResponse(schedule));
        }

        return schedulings;
    }

}
