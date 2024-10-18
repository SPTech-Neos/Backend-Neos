package school.sptech.neosspringjava.api.dtos.dashboardDto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import school.sptech.neosspringjava.domain.model.Establishment;

@Builder
public record TotalGainRequest(   
@NotNull
@NotBlank
@NotEmpty
Integer establishment,
@NotNull
@NotBlank
@NotEmpty
LocalDateTime start,
@NotNull
@NotBlank
@NotEmpty
LocalDateTime end) {

}
