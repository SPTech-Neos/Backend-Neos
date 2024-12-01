package school.sptech.neosspringjava.api.mappers;

import school.sptech.neosspringjava.api.dtos.orderDto.OrderRequest;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderResponse;
import school.sptech.neosspringjava.domain.model.Order;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderResponse toResponse(Order o){
        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new OrderResponse(
            o.getId(),
            datetime.toString(),
            o.getStatus(),
            o.getClient()
        );
    }

    public static List<OrderResponse> toResponse(List<Order> orders){
        return orders.stream().map(OrderMapper::toResponse).collect(Collectors.toList());
    }
}
