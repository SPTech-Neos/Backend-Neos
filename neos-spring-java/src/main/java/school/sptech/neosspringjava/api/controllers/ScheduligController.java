package school.sptech.neosspringjava.api.controllers;

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
import school.sptech.neosspringjava.api.dtos.schedulingDto.SchedulingRequest;
import school.sptech.neosspringjava.api.dtos.schedulingDto.SchedulingResponse;
import school.sptech.neosspringjava.api.mappers.ScheduligMapper;
import school.sptech.neosspringjava.domain.model.Scheduling;
import school.sptech.neosspringjava.domain.repository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.ServiceRepository;
import school.sptech.neosspringjava.service.SchedulingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedulings")
public class ScheduligController {

    private final SchedulingRepository schedulingRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduligMapper scheduligMapper;
    private final SchedulingService schedulingService;

    @GetMapping
    public ResponseEntity<List<SchedulingResponse>> getAllSchedulig() {
       
        List<Scheduling> scheduling = schedulingRepository.findAll();

        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponseList(scheduling));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponse> getScheduligById(@PathVariable int id) {
        Scheduling scheduling = schedulingRepository.findById(id).orElseThrow();
        if (scheduling == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponse(scheduling));
    }

    @PostMapping
    public ResponseEntity<SchedulingResponse> createSchedulig(@Valid @RequestBody SchedulingRequest schedulingRequest) {
   
        Scheduling scheduling = new Scheduling();
        scheduling.setService(serviceRepository.findById(schedulingRequest.idService()).orElseThrow());
        scheduling.setEmployee(employeeRepository.findById(schedulingRequest.idEmployee()).orElseThrow());
        scheduling.setClient(clientRepository.findById(schedulingRequest.idClient()).orElseThrow());
        scheduling.setDateTimeSchedule((schedulingRequest.dateTime()==null)?LocalDateTime.now(): schedulingRequest.dateTime());
        scheduling = schedulingRepository.save(scheduling);
        return ResponseEntity.ok().body(scheduligMapper.toScheduligResponse(scheduling));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponse> updateSchedulig(@PathVariable int id, @Valid @RequestBody SchedulingRequest schedulingRequest) {
        Scheduling scheduling = schedulingRepository.findById(id).orElseThrow();
        if (scheduling == null) {
            return ResponseEntity.notFound().build();
        }
        scheduling.setService(serviceRepository.findById(schedulingRequest.idService()).orElseThrow());
        scheduling.setEmployee(employeeRepository.findById(schedulingRequest.idEmployee()).orElseThrow());
        scheduling.setClient(clientRepository.findById(schedulingRequest.idClient()).orElseThrow());
        scheduling.setDateTimeSchedule((schedulingRequest.dateTime()==null)?LocalDateTime.now(): schedulingRequest.dateTime());
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
    public ResponseEntity<List<SchedulingResponse>> getSchedulesByClientId(@PathVariable Integer clientId) {
        List<SchedulingResponse> schedules = schedulingService.getSchedulesByClientId(clientId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<SchedulingResponse>> getSchedulesByEmployeeId(@PathVariable Integer employeeId) {
        List<SchedulingResponse> schedules = schedulingService.getSchedulesByEmployeeId(employeeId);
        return ResponseEntity.ok(schedules);
    }
}
