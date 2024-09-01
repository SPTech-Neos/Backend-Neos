package school.sptech.neosspringjava.api.dtos.dashboardDto;

import jakarta.validation.constraints.NotNull;

public record MarketRquest(
    @NotNull
    Integer establishmentId
) {

}
