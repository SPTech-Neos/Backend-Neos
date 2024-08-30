package school.sptech.neosspringjava.api.controllers.orderController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderResponse;
import school.sptech.neosspringjava.api.mappers.orderMapper.OrderMapper;
import school.sptech.neosspringjava.service.orderService.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService oService;

    @GetMapping
    public List<OrderResponse> findAllOrders(){
        return OrderMapper.toResponse(oService.findAll());
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Integer id){
        return OrderMapper.toResponse(oService.findById(id));
    }
}
