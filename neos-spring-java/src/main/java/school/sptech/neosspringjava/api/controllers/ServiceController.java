package school.sptech.neosspringjava.api.controllers;

import java.util.List;

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
    private final ServiceMapper serviceMapper;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> listService() {
        return ResponseEntity.ok().body(servServ.findAll());
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest serviceRequest) {
        return ResponseEntity.ok().body(servServ.save(serviceRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable Integer id, @RequestBody ServiceRequest serviceRequest) {
        return ResponseEntity.ok().body(servServ.update(id, serviceRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServiceResponse> partialUpdateService(@PathVariable Integer id, @RequestBody ServiceRequest updates) {
        ServiceResponse updatedService = servServ.partialUpdate(id, updates);
        return ResponseEntity.ok(updatedService);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@RequestParam Integer id) {
        return ResponseEntity.ok().body(serviceMapper.toServiceResponse(servServ.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@RequestParam Integer id) {
        servServ.deleteByid(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceResponse> updateServiceStatus(@PathVariable Integer id, @RequestParam String status) {
        return ResponseEntity.ok().body(servServ.updateServiceStatus(id, status));
    }

    @GetMapping("/by-establishment/{id}")
    public ResponseEntity<List<ServiceResponse>> findServicesByEstablishmentAndStatus(
        @PathVariable Integer id,
        @RequestParam String status) {
        return ResponseEntity.ok().body(servServ.findServicesByEstablishmentAndStatus(id, status));
    }

    @GetMapping("/by-establishment/{id}/status")
    public ResponseEntity<List<ServiceResponse>> findServicesByEstablishmentAndStatus(
        @PathVariable Integer id,
        @RequestParam Integer status) {
        return ResponseEntity.ok().body(servServ.findServicesByEstablishmentAndStatus(id, status));
    }

    @GetMapping("/by-employee/{id}")
    public ResponseEntity<List<ServiceResponse>> findServicesByEmployeeId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(servServ.findServicesByEmployeeId(id));
    }

    @GetMapping("/by-employee/{idEmployeee}/by-category/{categotyId}")
    public ResponseEntity<List<ServiceResponse>> findServicesByEmployeeIdAndServiceCategory(
        @PathVariable Integer idEmployeee,
        @PathVariable Integer categotyId) {
        return ResponseEntity.ok().body(servServ.findServicesByEmployeeIdAndServiceCategory(idEmployeee, categotyId));
    }
}
