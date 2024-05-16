package school.sptech.neosspringjava.api.controllers.serviceTypeController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/update/{id}")
    public ResponseEntity<ServiceTypeResponse> updateServiceType (@PathVariable Integer id, @RequestBody ServiceTypeRequest serviceTypeRequest){
        ServiceTypeRequest STReq = new ServiceTypeRequest(id, serviceTypeRequest.name(), serviceTypeRequest.ServiceCategory());
        return  ResponseEntity.ok().body(servTypeServ.save(STReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> getServiceTypeById(@PathVariable Integer id){
       return ResponseEntity.ok().body(servTypeServ.findById(id));
        
    }

    @PostMapping("/delete/id")
    public ResponseEntity<String> deleteServiceType (@PathVariable Integer id){
        return ResponseEntity.ok().body(servTypeServ.deleteByid(id));
    }
}