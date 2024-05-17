package school.sptech.neosspringjava.api.controllers.establishmentController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResquest;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

  
    private final EstablishmentRopository establishmentRopository;
    private final EstablishmentMapper establishmentMapper;
    private final LocalRepository localRepository;
    private final EstablishmentService establishmentService;


    @GetMapping
    public ResponseEntity<List<EstablishmentRespose>> findAll() {
        return ResponseEntity.ok(establishmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> findById(Integer id) {
        return ResponseEntity.ok(establishmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstablishmentRespose> save(EstablishmentResquest establishmentResquest) {
        return ResponseEntity.ok(establishmentService.save(establishmentResquest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> update(Integer id, EstablishmentResquest establishmentResquest) {

        return ResponseEntity.ok(establishmentService.save(establishmentResquest));
    }

    @DeleteMapping("/{id}")
    public void delete(Integer id) {
        establishmentService.delete(id);
    }

  
}
