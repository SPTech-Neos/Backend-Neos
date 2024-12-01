package school.sptech.neosspringjava.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.neosspringjava.api.dtos.orderDto.OrderRequest;
import school.sptech.neosspringjava.domain.repository.OrderRepository;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Order;

import java.time.LocalDateTime;
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
    // public List<Order> findAllOrdersByEstablishment(Integer id){
    //     return oRep.findAllOrdersByEstablishment(id);
    // }
    public List<Order> findAllOrdersByClient(Integer id){
        Client c = clientService.findById(id);
        if (c == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "cliente não encontrado");
        }
        List<Order> listOrder = oRep.findAllOrdersByClient(id);

        return listOrder;
    }

    public Order findById(Integer id){
        return oRep.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public Order save(OrderRequest oReq){
        Order o = new Order();
        LocalDateTime datetime = LocalDateTime.parse(oReq.dateTime());

        o.setDateTime(datetime);
        o.setStatus(sService.findById(oReq.fkStatus()));
        o.setClient(clientService.findById(oReq.fkClient()));

        return oRep.save(o);
    }

    public Order updateStatus(Integer id, Integer status){
        Order o = findById(id);
        o.setStatus(sService.findById(status));
        return oRep.save(o);
    }
    public Order updateOrder(Integer id, OrderRequest updateInformations){
        Order o = findById(id);

        LocalDateTime dateTime = LocalDateTime.parse(updateInformations.dateTime());

        if(dateTime != null ){
            o.setDateTime(dateTime);
            }
        if(updateInformations.fkClient() != null ){
            Client  c = clientService.findById(updateInformations.fkClient());
            o.setClient(c);
        }
        if(updateInformations.fkStatus() != null ){
            o.setStatus(sService.findById(updateInformations.fkStatus()));
            return oRep.save(o);
        }
            
        return oRep.save(o);
    }

    public void delete(Integer id){
        oRep.delete(findById(id));
    }

    // public List<Order> findByEstablishmentAndStatus(Integer id, Integer status){
    //     return oRep.findByEstablishmentAndStatus(id, status);
    // }

    public List<Order> findOrdersByClientIdAndStatusId(Integer id, Integer status){
        return oRep.findOrdersByClientIdAndStatusId(id, status);
    }


}

