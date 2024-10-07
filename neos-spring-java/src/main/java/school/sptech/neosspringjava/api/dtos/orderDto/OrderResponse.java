package school.sptech.neosspringjava.api.dtos.orderDto;

import lombok.Builder;
import lombok.Data;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Status;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(
        Integer id,
        LocalDateTime dateTime,
        Status status,
        Client client

) {
}
