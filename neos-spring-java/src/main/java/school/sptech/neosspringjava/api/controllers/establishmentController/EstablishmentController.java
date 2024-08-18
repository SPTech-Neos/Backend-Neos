package school.sptech.neosspringjava.api.controllers.establishmentController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponseList;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
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
        return ResponseEntity.ok(establishmentService.findAllActives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(establishmentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstablishmentResponse> save(@RequestBody EstablishmentRequest establishmentRequest) {
        return ResponseEntity.ok(establishmentService.save(establishmentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> update(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(establishmentService.update(establishmentRequest, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstablishmentResponse> partialUpdate(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(establishmentService.partialUpdate(establishmentRequest, id));
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<EstablishmentResponse> desativar(@PathVariable Integer id){
        return ResponseEntity.ok(establishmentService.inativarEstabelecimento(id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Integer id) {
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/full")
    public ResponseEntity<List<EstablishmentFullResponseList>> findFull() {
        return ResponseEntity.ok(establishmentService.findFull());
    }

    @GetMapping("/api/full/{id}")
    public ResponseEntity<List<EstablishmentFullResponse>> findAllFull(@PathVariable Integer id) {
        return ResponseEntity.ok(establishmentService.findAllFull(id));
    }
  
}
