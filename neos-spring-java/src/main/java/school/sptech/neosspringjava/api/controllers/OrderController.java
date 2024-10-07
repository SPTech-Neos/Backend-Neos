package school.sptech.neosspringjava.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderRequest;
import school.sptech.neosspringjava.api.dtos.orderDto.OrderResponse;
import school.sptech.neosspringjava.api.mappers.OrderMapper;
import school.sptech.neosspringjava.service.OrderService;

import java.util.List;
 
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService oService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders(){
        return ResponseEntity.ok(OrderMapper.toResponse(oService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(OrderMapper.toResponse(oService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest r){
        return ResponseEntity.ok(OrderMapper.toResponse(oService.save(r)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Integer id, @RequestParam Integer status){
        return ResponseEntity.ok(OrderMapper.toResponse(oService.updateStatus(id, status)));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Integer id,@RequestBody OrderRequest r ){
        return ResponseEntity.ok(OrderMapper.toResponse(oService.updateOrder(id, r)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        oService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-client/{id}/status")
    public ResponseEntity<List<OrderResponse>> findOrdersByClientIdAndStatusId(@PathVariable Integer id, @RequestParam Integer status){
        return ResponseEntity.ok(OrderMapper.toResponse(oService.findOrdersByClientIdAndStatusId(id, status)));
    }

    // @GetMapping("/by-establishment/{id}/status?={status}")
    // public ResponseEntity<List<OrderResponse>> findByEstablishmentAndStatus(@PathVariable Integer id, @PathVariable Integer status){
    //     return ResponseEntity.ok(OrderMapper.toResponse(oService.findByEstablishmentAndStatus(id, status)));
    // }


}
