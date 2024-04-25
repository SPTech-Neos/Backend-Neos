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

import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;

@RestController
@RequestMapping("/Establishments")
public class EstablishmentController {

    @Autowired
    private EstablishmentRopository EstablishmentRepository;

    @GetMapping
    public ResponseEntity<List<Establishment>> listEstablishment() {
        List<Establishment> lstEstablishments = EstablishmentRepository.findAll();
        return lstEstablishments.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstEstablishments);
    }

    @PostMapping
    public ResponseEntity<Establishment> postEstablishment(Establishment establishment) {

        Establishment establishmentCadastrado = EstablishmentRepository.save(establishment);
        return ResponseEntity.status(201).body(establishmentCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Establishment> updateMechant(Integer id, Establishment Establishment) {

        if (!EstablishmentRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Establishment.setIdEstablishment(id);
        Establishment establishmentAtualizado = EstablishmentRepository.save(Establishment);
        return ResponseEntity.status(200).body(establishmentAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Establishment> deleteEstablishment(Integer id) {

        if (!EstablishmentRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        EstablishmentRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
