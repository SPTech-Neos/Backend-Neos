package school.sptech.neosspringjava.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.service.csv.csvOrganization;

@RestController
@RequestMapping("/csv")
public class AcsvController {

    csvOrganization csvOrg = new csvOrganization();

    @GetMapping("/note")
    public ResponseEntity<String> gerarNota(@RequestBody List<Scheduling> scheduling) {
        //Optional<Scheduling> schedulingOptional = schedulingRepository.findById(1);
        // if (schedulingOptional.isPresent()) {
        //     Scheduling scheduling = schedulingOptional.get();
        //     csvOrg.generateSchedulingNote(scheduling);
        // }
        if (scheduling.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(csvOrg.generateSchedulingNote(scheduling));
        
        }

    }

    @Autowired
    EstablishmentRepository establishmentRepository;

    @GetMapping("/report")
    public ResponseEntity<String> gerarRelatorio(@RequestBody Establishment foundEstablishment) {
        // Optional<Establishment> establishment = establishmentRopository.findById(1);
        // if (establishment.isPresent()) {
        //     Establishment foundEstablishment = establishment.get();
        //     csvOrg.generateSchedulingReport(foundEstablishment);
        // }
        if (foundEstablishment == null) {
            return ResponseEntity.notFound().build();
        }else{
        return ResponseEntity.ok().body(csvOrg.generateSchedulingReport(foundEstablishment));
        }

    }
}
