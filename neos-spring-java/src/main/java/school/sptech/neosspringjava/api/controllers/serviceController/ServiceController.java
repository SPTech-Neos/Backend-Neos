package school.sptech.neosspringjava.api.controllers.serviceController;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.service.serviceService.ServiceService;

@RestController
@RequestMapping("/service")
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
    public ResponseEntity<ServiceResponse> partialUpdateService(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
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
}