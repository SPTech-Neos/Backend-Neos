package school.sptech.neosspringjava.api.dtos.marketDto;

import school.sptech.neosspringjava.api.dtos.orderDto.OrderResponse;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.domain.model.Order;
import school.sptech.neosspringjava.domain.model.Product;

public record MarketResponse(
        Integer id,
        Integer quantity,
        ProductResponse product,
        OrderResponse order
) {
}
