package school.sptech.neosspringjava.api.dtos.orderDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public record OrderRequest(
        @FutureOrPresent
        @NotNull
        LocalDateTime dateTime,
        @NotNull
        Integer fkStatus,
        @NotNull
        Integer fkClient
) {
}
