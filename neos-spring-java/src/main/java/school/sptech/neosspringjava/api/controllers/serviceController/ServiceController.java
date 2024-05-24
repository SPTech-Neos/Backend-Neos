package school.sptech.neosspringjava.api.controllers.serviceController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
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

    @PostMapping("/update/{id}")
    public ResponseEntity<ServiceResponse> updateService (@PathVariable Integer id, @RequestBody ServiceRequest serviceRequest){
        ServiceRequest STReq = new ServiceRequest(id, serviceRequest.specification(), serviceRequest.serviceType());
        return  ResponseEntity.ok().body(servServ.save(STReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable Integer id){
       return ResponseEntity.ok().body(servServ.findById(id));
        
    }

    @PostMapping("/delete/id")
    public ResponseEntity<String> deleteService (@PathVariable Integer id){
        return ResponseEntity.ok().body(servServ.deleteByid(id));
    }
}