package school.sptech.neosspringjava.api.controllers.establishmentController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

  
    private final EstablishmentService establishmentService;


    @GetMapping
    public ResponseEntity<List<EstablishmentRespose>> findAll() {
        return ResponseEntity.ok(establishmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(establishmentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


        
    }

    @PostMapping
    public ResponseEntity<EstablishmentRespose> save(@RequestBody EstablishmentRequest establishmentRequest) {
        return ResponseEntity.ok(establishmentService.save(establishmentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> update(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(establishmentService.update(establishmentRequest, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> partialUpdate(@RequestBody EstablishmentRequest establishmentRequest, @PathVariable Integer id) {
        return ResponseEntity.ok(establishmentService.partialUpdate(establishmentRequest, id));
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Integer id) {
        establishmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

  
}
