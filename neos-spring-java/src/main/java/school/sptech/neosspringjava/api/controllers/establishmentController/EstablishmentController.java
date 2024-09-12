package school.sptech.neosspringjava.api.controllers.establishmentController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;

import school.sptech.neosspringjava.api.mappers.serviceMapper.ServiceMapper;
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
        List<Establishment> e = establishmentService.findAll();

        List<EstablishmentResponse> eDtos = EstablishmentMapper.toEstablishmentResponseList(e, medias);
        return ResponseEntity.ok(eDtos);
    }

    @GetMapping("/active")
    public ResponseEntity<List<EstablishmentResponse>> findAllActives() {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponseList(establishmentService.findAllActives()));
    }
    @GetMapping("/inactive")
    public ResponseEntity<List<EstablishmentResponse>> findAllInatives() {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponseList(establishmentService.findAllInatives()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> findById(@PathVariable Integer id) {

        Establishment e = establishmentService.findById(id);
        Double media = establishmentService.findMediaById(id);

        EstablishmentResponse eDto = new EstablishmentResponse(e, media);

        return ResponseEntity.ok(eDto);
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceResponse>> findServicesById(@PathVariable Integer id) {
        // Retorna a lista de serviços convertida para ServiceResponse
        return ResponseEntity.ok().body(establishmentService.findServicesById(id));
    }

    @PostMapping
    public ResponseEntity<EstablishmentResponse> save(@RequestBody EstablishmentRequest establishmentRequest) {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.save(establishmentRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> update(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.update(establishmentRequest, id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> partialUpdate(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.partialUpdate(establishmentRequest, id)));
    }

    @PatchMapping("/deactive/{id}")
    public ResponseEntity<EstablishmentResponse> deactive(@PathVariable Integer id){
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.inactiveEstablishment(id)));
    }
    @PatchMapping("/reactive/{id}")
    public ResponseEntity<EstablishmentResponse> reactive(@PathVariable Integer id){
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.reactive(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/best-rating/{top}")
    public ResponseEntity<List<EstablishmentResponse>> findBestRateds(@PathVariable Integer top){
        List<Establishment> e = establishmentService.findBestRateds(top);
        List<Double> medias = establishmentService.findBestMedias(top);

        List<EstablishmentResponse> eDtos = EstablishmentMapper.toEstablishmentResponseList(e, medias);
        return ResponseEntity.ok(eDtos);
    }

//    @GetMapping("/api/full")
//    public ResponseEntity<List<EstablishmentFullResponseList>> findFull() {
//        return ResponseEntity.ok(establishmentService.findFull());
//    }
//
//    @GetMapping("/api/full/{id}")
//    public ResponseEntity<List<EstablishmentFullResponse>> findAllFull(@PathVariable Integer id) {
//        return ResponseEntity.ok(establishmentService.findAllFull(id));
//    }
  
}
