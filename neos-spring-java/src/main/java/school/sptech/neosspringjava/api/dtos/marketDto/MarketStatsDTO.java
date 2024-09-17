package school.sptech.neosspringjava.api.dtos.marketDto;

import lombok.Builder;

@Builder
public record MarketStatsDTO(

        String period,
        Integer canceledOrders,
        Integer totalOrders) {

}
