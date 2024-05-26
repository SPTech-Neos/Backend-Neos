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

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {

  
    private final EstablishmentRopository establishmentRopository;
    private final EstablishmentMapper establishmentMapper;
    private final LocalRepository localRepository;


    @GetMapping
    public ResponseEntity<List<EstablishmentRespose>> findAll() {
        return ResponseEntity.ok(establishmentMapper.toEstablishmentResponse(establishmentRopository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> findById(Integer id) {
        return ResponseEntity.ok(establishmentMapper.toEstablishmentResponse(establishmentRopository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<EstablishmentRespose> save(@RequestBody EstablishmentRequest establishmentRequest) {
        Establishment establishment = Establishment.builder()
                .name(establishmentRequest.name())
                .local(establishmentRequest.local())
                .build();
        return ResponseEntity.ok(establishmentMapper.toEstablishmentResponse(establishmentRopository.save(establishment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentRespose> update(@PathVariable Integer id, @RequestBody EstablishmentRequest establishmentRequest) {
        Optional<Establishment> optionalEstablishment = establishmentRopository.findById(id);
        if (optionalEstablishment.isPresent()) {
            Establishment establishment = optionalEstablishment.get();
            establishment.setName(establishmentRequest.name());
            establishment.setLocal(establishmentRequest.local());
            // Atualize outros campos conforme necessário
            Establishment updatedEstablishment = establishmentRopository.save(establishment);
            return ResponseEntity.ok(establishmentMapper.toEstablishmentResponse(updatedEstablishment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public void delete(Integer id) {
        establishmentRopository.deleteById(id);
    }

  
}
