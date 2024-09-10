package school.sptech.neosspringjava.api.dtos.dashboardDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record QuantityStatusDto(
    @NotBlank
    @NotEmpty
    @NotNull
    Integer quantity,
    @NotBlank
    @NotEmpty
    @NotNull
    String status
) {
}