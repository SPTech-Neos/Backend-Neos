package school.sptech.neosspringjava.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.mappers.EstablishmentMapper;
import school.sptech.neosspringjava.api.mappers.ServiceMapper;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.service.EstablishmentService;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceRequest;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @GetMapping
    public ResponseEntity<List<EstablishmentResponse>> findAll() {
        List<Double> medias = establishmentService.findAllMediasEstablishmentOrder();
        List<Establishment> establishments = establishmentService.findAll();

        List<EstablishmentResponse> establishmentResponses = EstablishmentMapper.toEstablishmentResponseList(establishments, medias);
        return ResponseEntity.ok(establishmentResponses);
    }

    @GetMapping("/active")
    public ResponseEntity<List<EstablishmentResponse>> findAllActives() {
        List<EstablishmentResponse> establishmentResponses = EstablishmentMapper.toEstablishmentResponseList(establishmentService.findAllActives());
        return ResponseEntity.ok(establishmentResponses);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<EstablishmentResponse>> findAllInactives() {
        List<EstablishmentResponse> establishmentResponses = EstablishmentMapper.toEstablishmentResponseList(establishmentService.findAllInatives());
        return ResponseEntity.ok(establishmentResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> findById(@PathVariable Integer id) {
        Establishment establishment = establishmentService.findById(id);
        if (establishment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Double media = establishmentService.findMediaById(id);
        EstablishmentResponse establishmentResponse = new EstablishmentResponse(establishment, media);
        return ResponseEntity.ok(establishmentResponse);
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceResponse>> findServicesById(@PathVariable Integer id) {
        List<ServiceResponse> services = establishmentService.findServicesById(id);
        if (services == null || services.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<EstablishmentResponse> save(@RequestBody EstablishmentRequest establishmentRequest) {
        Establishment establishment = establishmentService.save(establishmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(EstablishmentMapper.toEstablishmentResponse(establishment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> update(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        Establishment establishment = establishmentService.update(establishmentRequest, id);
        if (establishment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishment));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> partialUpdate(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        Establishment establishment = establishmentService.partialUpdate(establishmentRequest, id);
        if (establishment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishment));
    }

    @PatchMapping("/deactive/{id}")
    public ResponseEntity<EstablishmentResponse> deactive(@PathVariable Integer id) {
        Establishment establishment = establishmentService.inactiveEstablishment(id);
        if (establishment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishment));
    }

    @PatchMapping("/reactive/{id}")
    public ResponseEntity<EstablishmentResponse> reactive(@PathVariable Integer id) {
        Establishment establishment = establishmentService.reactive(id);
        if (establishment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (establishmentService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/best-rating/{top}")
    public ResponseEntity<List<EstablishmentResponse>> findBestRateds(@PathVariable Integer top) {
        List<Establishment> establishments = establishmentService.findBestRateds(top);
        List<Double> medias = establishmentService.findBestMedias(top);

        List<EstablishmentResponse> establishmentResponses = EstablishmentMapper.toEstablishmentResponseList(establishments, medias);
        return ResponseEntity.ok(establishmentResponses);
    }
}
