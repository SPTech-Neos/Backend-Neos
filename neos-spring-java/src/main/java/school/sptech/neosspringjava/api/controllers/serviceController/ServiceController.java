package school.sptech.neosspringjava.api.controllers.serviceController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final ServiceTypeRepository serviceTypeRepository;
    private final FilterRepository filterRepository;

    
    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getServices() {
        return ResponseEntity.ok(serviceMapper.toServiceResponseList(serviceRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@RequestParam Integer id) {
        return ResponseEntity.ok(serviceMapper.toServiceResponse(serviceRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest serviceRequest) {

        Service service = new Service();
        service.setSpecification(serviceRequest.specification());
        // TODO: corrigir o erro abaixo
        // service.setServiceType(serviceTypeRepository.findById(serviceRequest.fkServiceType()).get());
        return ResponseEntity.status(201).body(serviceMapper.toServiceResponse(serviceRepository.save(service)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@RequestParam Integer id, @RequestBody ServiceRequest serviceRequest) {
        Service serviceToUpdate = serviceRepository.findById(id).get();
        serviceToUpdate.setSpecification(serviceRequest.specification());
        // TODO: corrigir o erro abaixo
        // serviceToUpdate.setServiceType(serviceTypeRepository.findById(serviceRequest.fkServiceType()).get());
        return ResponseEntity.ok(serviceMapper.toServiceResponse(serviceRepository.save(serviceToUpdate)));
    }   


    @DeleteMapping("/{id}")
    public void deleteService(@RequestParam Integer id) {
        serviceRepository.deleteById(id);
    }

}
