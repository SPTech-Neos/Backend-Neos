package school.sptech.neosspringjava.api.dtos.dashboardDto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record MarketRquest(
    @NotNull
    Integer establishmentId,
    LocalDateTime start,
    LocalDateTime end
) {

}
