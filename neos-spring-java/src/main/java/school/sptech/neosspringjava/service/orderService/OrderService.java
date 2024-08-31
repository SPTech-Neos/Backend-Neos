package school.sptech.neosspringjava.service.orderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderRequest;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.repository.orderRepositiory.OrderRepository;
import school.sptech.neosspringjava.service.client.ClientService;
import school.sptech.neosspringjava.service.productService.ProductService;
import school.sptech.neosspringjava.service.statusService.StatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository oRep;
    private final StatusService sService;
    private final ClientService clientService;

    public List<Order> findAll(){
        return oRep.findAll();
    }

    public Order findById(Integer id){
        return oRep.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Order save(OrderRequest oReq){
        Order o = new Order();

        o.setDateTime(oReq.dateTime());
        o.setStatus(sService.findById(oReq.fkStatus()));
        o.setClient(clientService.findById(oReq.fkClient()));

        return oRep.save(o);
    }


}

