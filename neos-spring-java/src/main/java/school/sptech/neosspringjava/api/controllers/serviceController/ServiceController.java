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
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final ServiceTypeRepository serviceTypeRepository;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> findAll() {
        return ResponseEntity.ok(serviceMapper.toServiceResponseList(serviceRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(serviceMapper.toServiceResponse(serviceRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> save(@RequestBody ServiceRequest serviceRequest) {
        Service service = Service.builder()
                .specification(serviceRequest.specification())
                .serviceType(serviceTypeRepository.findById(serviceRequest.fkServiceType()).get())
                .build();
        return ResponseEntity.ok(serviceMapper.toServiceResponse(serviceRepository.save(service)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> update(@RequestParam Integer id, @RequestBody ServiceRequest serviceRequest) {
        Service service = Service.builder()
                .id(id)
                .specification(serviceRequest.specification())
                .serviceType(serviceTypeRepository.findById(serviceRequest.fkServiceType()).get())
                .build();
        return ResponseEntity.ok(serviceMapper.toServiceResponse(serviceRepository.save(service)));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Integer id) {
        serviceRepository.deleteById(id);
    }
    
}
