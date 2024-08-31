package school.sptech.neosspringjava.api.dtos.marketDto;

import jakarta.validation.constraints.NotNull;

public record MarketRequest(
        @NotNull
        Integer quantity,
        @NotNull
        Integer fkProduct,
        @NotNull
        Integer fkOrder
) {
}
