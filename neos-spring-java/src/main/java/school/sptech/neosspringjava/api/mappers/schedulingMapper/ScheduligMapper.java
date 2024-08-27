package school.sptech.neosspringjava.api.mappers.schedulingMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.schedulingDto.ScheduligResponse;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;

@Component
public class ScheduligMapper {


    public static ScheduligResponse toScheduligResponse(Scheduling scheduling) {
        return new ScheduligResponse(scheduling.getId(), scheduling.getClient(), scheduling.getService(), scheduling.getEmployee(), scheduling.getStatus(), scheduling.getDateTime());
    }

    public List<ScheduligResponse> toScheduligResponseList(List<Scheduling> schedulings) {
        return schedulings.stream().map(ScheduligMapper::toScheduligResponse).collect(Collectors.toList());
    }


}
