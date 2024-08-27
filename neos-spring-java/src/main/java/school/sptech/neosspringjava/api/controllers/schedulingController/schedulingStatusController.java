package school.sptech.neosspringjava.api.controllers.schedulingController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.schedulingStatusDto.schedulingStatusRequest;
import school.sptech.neosspringjava.api.dtos.schedulingStatusDto.schedulingStatusResponse;
import school.sptech.neosspringjava.domain.model.schedulingStatus.schedulingStatus;
import school.sptech.neosspringjava.service.schedulingStatusService.SchedulingStatusService;

@RestController
@RequestMapping("/schedulingStatus")
@RequiredArgsConstructor
public class schedulingStatusController {

    private final SchedulingStatusService schedulingStatusService;

    @GetMapping
    public ResponseEntity<List<schedulingStatus>> listSchedulingStatus() {
        return ResponseEntity.ok().body(schedulingStatusService.findAll());
    }

    @PostMapping
    public ResponseEntity<schedulingStatus> createSchedulingStatus(@RequestBody schedulingStatusRequest schedulingStatusRequest) {
        return ResponseEntity.ok().body(schedulingStatusService.save(schedulingStatusRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<schedulingStatus> updateSchedulingStatus(@PathVariable Integer id,
            @RequestBody schedulingStatusRequest schedulingStatusRequest) {
        return ResponseEntity.ok().body(schedulingStatusService.update(id, schedulingStatusRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<schedulingStatus> getSchedulingStatusById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(schedulingStatusService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedulingStatus(@PathVariable Integer id) {
        schedulingStatusService.deleteByid(id);
        return ResponseEntity.ok().build();
    }
}
