package school.sptech.neosspringjava.api.dtos.schedulingDto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record SchedulingRequest(LocalDateTime dateTime, Integer idScheduligStatus, Integer idService, Integer idEmployee, Integer idClient) {
}
