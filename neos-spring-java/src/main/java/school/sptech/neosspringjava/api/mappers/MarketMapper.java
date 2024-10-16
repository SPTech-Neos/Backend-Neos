package school.sptech.neosspringjava.api.mappers;

import school.sptech.neosspringjava.api.dtos.marketDto.MarketResponse;
import school.sptech.neosspringjava.domain.model.Market;

import java.util.List;
import java.util.stream.Collectors;

public class MarketMapper {
    public static MarketResponse toResponse(Market m){
        MarketResponse mDto = new MarketResponse(
                m.getId(),
                m.getQuantity(),
                ProductMapper.toProductResponse(m.getProduct()),
                OrderMapper.toResponse(m.getOrder())
        );

        return mDto;
    }

    public static List<MarketResponse> toResponse(List<Market> markets){
        return markets.stream().map(MarketMapper::toResponse).collect(Collectors.toList());
    }
}
