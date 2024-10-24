package school.sptech.neosspringjava.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.ServiceMapper;
import school.sptech.neosspringjava.service.ServiceService;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService servServ;


    @GetMapping
    public ResponseEntity<List<ServiceResponse>> listService (){
       return ResponseEntity.ok().body(servServ.findAll());
    }
    
    @PostMapping
    public ResponseEntity<ServiceResponse>  createService (@RequestBody ServiceRequest serviceRequest){
       return ResponseEntity.ok().body(servServ.save(serviceRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService (@PathVariable Integer id, @RequestBody ServiceRequest serviceRequest){
        return ResponseEntity.ok().body(servServ.update(id, serviceRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceResponse> partialUpdateService(@PathVariable Integer id, @RequestBody ServiceRequest updates) {
        ServiceResponse updatedService = servServ.partialUpdate(id, updates);
        return ResponseEntity.ok(updatedService);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Integer id){
       return ResponseEntity.ok().body(ServiceMapper.toServiceResponse(servServ.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService (@PathVariable Integer id){
        servServ.deleteByid(id);
        return ResponseEntity.ok().build();
    }

    // @PatchMapping("/{id}/status?={status}")
    // public ResponseEntity<ServiceResponse> updateServiceStatus(@PathVariable Integer id, @PathVariable String status){
    //     return ResponseEntity.ok().body(servServ.updateServiceStatus(id, status));
    // }

    // @GetMapping("/by-establishment/{id}/status?={status}")
    // public ResponseEntity<List<ServiceResponse>> findServicesByEstablishmentIdAndStatus(@PathVariable Integer id, @PathVariable String status){
    //     return ResponseEntity.ok().body(servServ.findServicesByEstablishmentIdAndStatus(id, status));
    // }
}