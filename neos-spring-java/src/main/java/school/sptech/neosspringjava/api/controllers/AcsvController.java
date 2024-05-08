package school.sptech.neosspringjava.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.neosspringjava.config.csvOrganization;
import school.sptech.neosspringjava.config.mocData;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;

@RestController
@RequestMapping("/csv")
public class AcsvController {

    @Autowired
    private mocData dataInitializer;

    csvOrganization csvOrg = new csvOrganization();

    @GetMapping
    public ResponseEntity<Boolean> moc() {
    try {
        // Chama o método run de mocData
        dataInitializer.run();
       
        // Retorna uma resposta indicando que a operação foi bem-sucedida
        return ResponseEntity.ok().body(true);
    } catch (Exception e) {
        // Se ocorrer uma exceção, retorna uma resposta indicando que houve um erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }
    }

    @Autowired
    SchedulingRepository schedulingRepository;

    @GetMapping("/note")
    public void gerarNota() {
        Optional<Scheduling> schedulingOptional = schedulingRepository.findById(1);
        if (schedulingOptional.isPresent()) {
            Scheduling scheduling = schedulingOptional.get();
            csvOrg.generateSchedulingNote(scheduling);
        }
    }

    @Autowired
    EstablishmentRopository establishmentRopository;

    @GetMapping("/report")
    public void gerarRelatorio() {
        Optional<Establishment> establishment = establishmentRopository.findById(1);
        if (establishment.isPresent()) {
            Establishment foundEstablishment = establishment.get();
            csvOrg.generateSchedulingReport(foundEstablishment);
        }

    }
}
