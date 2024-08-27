package school.sptech.neosspringjava.api.controllers.schedulingController;

import java.time.LocalDateTime;
import java.util.List;

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
import school.sptech.neosspringjava.api.dtos.schedulingDto.ScheduligRequest;
import school.sptech.neosspringjava.api.dtos.schedulingDto.ScheduligResponse;
import school.sptech.neosspringjava.api.mappers.schedulingMapper.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.service.schedulingService.SchedulingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduling")
public class ScheduligController {

    private final SchedulingRepository schedulingRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;private final ScheduligMapper scheduligMapper;
    private final SchedulingService schedulingService;

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
   
        Scheduling scheduling = new Scheduling();
        scheduling.setService(serviceRepository.findById(scheduligRequest.idService()).orElseThrow());
        scheduling.setEmployee(employeeRepository.findById(scheduligRequest.idEmployee()).orElseThrow());
        scheduling.setClient(clientRepository.findById(scheduligRequest.idClient()).orElseThrow());
        scheduling.setDateTime((scheduligRequest.dateTime()==null)?LocalDateTime.now():scheduligRequest.dateTime());
        scheduling = schedulingRepository.save(scheduling);
        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponse(scheduling));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScheduligResponse> updateSchedulig(@PathVariable int id, @Valid @RequestBody ScheduligRequest scheduligRequest) {
        Scheduling scheduling = schedulingRepository.findById(id).orElseThrow();
        if (scheduling == null) {
            return ResponseEntity.notFound().build();
        }
        scheduling.setService(serviceRepository.findById(scheduligRequest.idService()).orElseThrow());
        scheduling.setEmployee(employeeRepository.findById(scheduligRequest.idEmployee()).orElseThrow());
        scheduling.setClient(clientRepository.findById(scheduligRequest.idClient()).orElseThrow());
        scheduling.setDateTime((scheduligRequest.dateTime()==null)?LocalDateTime.now():scheduligRequest.dateTime());
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

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ScheduligResponse>> getSchedulesByClientId(@PathVariable Integer clientId) {
        List<ScheduligResponse> schedules = schedulingService.getSchedulesByClientId(clientId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ScheduligResponse>> getSchedulesByEmployeeId(@PathVariable Integer employeeId) {
        List<ScheduligResponse> schedules = schedulingService.getSchedulesByEmployeeId(employeeId);
        return ResponseEntity.ok(schedules);
    }
}
