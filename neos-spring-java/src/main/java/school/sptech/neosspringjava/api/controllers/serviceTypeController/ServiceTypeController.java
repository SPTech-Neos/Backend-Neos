package school.sptech.neosspringjava.api.controllers.serviceTypeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeRequest;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.service.serviceTypeService.ServiceTypeService;

@RestController
@RequestMapping("/serviceType")
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeService servTypeServ;


    @GetMapping
    public ResponseEntity<List<ServiceTypeResponse>> listServiceType (){
       return ResponseEntity.ok().body(servTypeServ.findAll());
    }
    
    @PostMapping
    public ResponseEntity<ServiceTypeResponse>  createServiceType (@RequestBody ServiceTypeRequest serviceTypeRequest){
       return ResponseEntity.ok().body(servTypeServ.save(serviceTypeRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> updateServiceType (@PathVariable Integer id, @RequestBody ServiceTypeRequest serviceTypeRequest){
        return  ResponseEntity.ok().body(servTypeServ.update(serviceTypeRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> getServiceTypeById(@PathVariable Integer id){
       return ResponseEntity.ok().body(servTypeServ.findById(id));
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceType (@PathVariable Integer id){
        return ResponseEntity.ok().body(servTypeServ.deleteByid(id));
    }
}