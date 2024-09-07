package school.sptech.neosspringjava.service.orderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderRequest;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.repository.orderRepositiory.OrderRepository;
import school.sptech.neosspringjava.service.client.ClientService;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;
import school.sptech.neosspringjava.service.productService.ProductService;
import school.sptech.neosspringjava.service.statusService.StatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository oRep;
    private final StatusService sService;
    private final ClientService clientService;
    private final EmployeeService eService;

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
        o.setEmployee(eService.findById(oReq.fkEmployee()));


        return oRep.save(o);
    }

    public Order update(Integer id, OrderRequest oReq){
        Order o = findById(id);

        o.setDateTime(oReq.dateTime());
        o.setStatus(oReq.fkStatus() == null ? o.getStatus() : sService.findById(oReq.fkStatus()));
        o.setClient(oReq.fkClient() == null ? o.getClient() : clientService.findById(oReq.fkClient()));
        o.setEmployee(oReq.fkEmployee() == null ? o.getEmployee() : eService.findById(oReq.fkEmployee()));
        

        return oRep.save(o);
    }

    public Order updateStatus(Integer id, Integer status){
        Order o = findById(id);

        if(status == null){
            throw new IllegalArgumentException("Status não pode ser nulo");
        }


        o.setStatus(sService.findById(status));

        return oRep.save(o);
    }

    public void delete(Integer id){
        oRep.delete(findById(id));
    }

    public List<Order> findByEstablishmentAndStatus(Integer id, Integer status){
        return oRep.findByEstablishmentAndStatus(id, status);
    }

    public List<Order> findByClientAndStatus(Integer id, Integer status){
        return oRep.findByClientAndStatus(id, status);
    }

    public List<Order> findByEmployeeAndStatus(Integer id, Integer status){
        return oRep.findByEmployeeAndStatus(id, status);
    }

}

