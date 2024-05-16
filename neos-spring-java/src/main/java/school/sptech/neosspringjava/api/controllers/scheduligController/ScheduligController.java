package school.sptech.neosspringjava.api.controllers.scheduligController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.scheduligDto.ScheduligRequest;
import school.sptech.neosspringjava.api.dtos.scheduligDto.ScheduligResponse;
import school.sptech.neosspringjava.api.mappers.scheduligMapper.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class ScheduligController {

    private final SchedulingRepository schedulingRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduligMapper scheduligMapper;

    @GetMapping
    public ResponseEntity<List<ScheduligResponse>> getAllSchedulig() {
       
        List<Scheduling> scheduling = schedulingRepository.findAll();

        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponseList(scheduling));
    }

    @GetMapping("/{id}")

    public ResponseEntity<ScheduligResponse> getScheduligById(@PathVariable int id) {
        Scheduling scheduling = schedulingRepository.findById(id).orElseThrow();
        if (scheduling == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponse(scheduling));
    }

    @PostMapping
    public ResponseEntity<ScheduligResponse> createSchedulig(@Valid @RequestBody ScheduligRequest scheduligRequest) {
   
        Scheduling scheduling = Scheduling.builder()
                .client(clientRepository.findById(scheduligRequest.idClient()).orElseThrow())
                .service(serviceRepository.findById(scheduligRequest.idService()).orElseThrow())
                .employee(employeeRepository.findById(scheduligRequest.idEmployee()).orElseThrow())
                .dateTime(LocalDateTime.now())
                .build();

        scheduling = schedulingRepository.save(scheduling);
        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponse(scheduling));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScheduligResponse> updateSchedulig(@PathVariable int id, @Valid @RequestBody ScheduligRequest scheduligRequest) {
        Scheduling scheduling = schedulingRepository.findById(id).orElseThrow();
        if (scheduling == null) {
            return ResponseEntity.notFound().build();
        }
        scheduling.setClient(clientRepository.findById(scheduligRequest.idClient()).orElseThrow());
        scheduling.setService(serviceRepository.findById(scheduligRequest.idService()).orElseThrow());
        scheduling.setEmployee(employeeRepository.findById(scheduligRequest.idEmployee()).orElseThrow());

        scheduling = schedulingRepository.save(scheduling);
        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponse(scheduling));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedulig(@PathVariable int id) {
        Scheduling scheduling = schedulingRepository.findById(id).orElseThrow();
        if (scheduling == null) {
            return ResponseEntity.notFound().build();
        }
        schedulingRepository.delete(scheduling);
        return ResponseEntity.ok().build();
    }
}
