package school.sptech.neosspringjava.api.mappers;

import school.sptech.neosspringjava.api.dtos.orderDto.OrderRequest;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderResponse;
import school.sptech.neosspringjava.domain.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderResponse toResponse(Order o){
        return new OrderResponse(
            o.getId(),
            o.getDateTime(),
            o.getStatus(),
            o.getClient()
        );
    }

    public static List<OrderResponse> toResponse(List<Order> orders){
        return orders.stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }
}
