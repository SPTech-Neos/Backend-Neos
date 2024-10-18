package school.sptech.neosspringjava.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeRequest;
import school.sptech.neosspringjava.api.dtos.serviceTypeDto.ServiceTypeResponse;
import school.sptech.neosspringjava.service.ServiceTypeService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/service-types")
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

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> getServiceTypeById(@PathVariable Integer id){
       return ResponseEntity.ok().body(servTypeServ.findById(id));
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> updateServiceType (@RequestBody ServiceTypeRequest serviceTypeRequest, @PathVariable Integer id){
        return ResponseEntity.ok().body(servTypeServ.update(serviceTypeRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceType (@PathVariable Integer id){
        servTypeServ.delete(id);
        return ResponseEntity.ok().build();
    }

}