package school.sptech.neosspringjava.service.orderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.repository.orderRepositiory.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository oRep;

    public List<Order> findAll(){
        return oRep.findAll();
    }

    public Order findById(Integer id){
        return oRep.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }



}

