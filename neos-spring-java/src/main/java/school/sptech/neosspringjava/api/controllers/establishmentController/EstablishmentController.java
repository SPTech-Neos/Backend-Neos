package school.sptech.neosspringjava.api.controllers.establishmentController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponseList;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @GetMapping
    public ResponseEntity<List<EstablishmentResponse>> findAll() {
        return ResponseEntity.ok(establishmentService.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EstablishmentResponse>> findAllActives() {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponseList(establishmentService.findAllActives()));
    }
    @GetMapping("/inative")
    public ResponseEntity<List<EstablishmentResponse>> findAllInatives() {
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponseList(establishmentService.findAllInatives()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> findById(@PathVariable Integer id) {

        EstablishmentResponse e = establishmentService.findById(id);

        return ResponseEntity.ok(e);
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

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<EstablishmentResponse> inactive(@PathVariable Integer id){
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.inactiveEstablishment(id)));
    }
    @PatchMapping("/reactive/{id}")
    public ResponseEntity<EstablishmentResponse> active(@PathVariable Integer id){
        return ResponseEntity.ok(EstablishmentMapper.toEstablishmentResponse(establishmentService.reactive(id)));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Integer id) {
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
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
