package school.sptech.neosspringjava.api.mappers.scheduligMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.scheduligDto.ScheduligResponse;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;

@Component
public class ScheduligMapper {


    public static ScheduligResponse toScheduligResponse(Scheduling scheduling) {
        return new ScheduligResponse(scheduling.getId(), scheduling.getClient(), scheduling.getService(), scheduling.getEmployee());
    }

    public List<ScheduligResponse> toScheduligResponse(List<Scheduling> schedulings) {
        return schedulings.stream().map(ScheduligMapper::toScheduligResponse).collect(Collectors.toList());
    }


}