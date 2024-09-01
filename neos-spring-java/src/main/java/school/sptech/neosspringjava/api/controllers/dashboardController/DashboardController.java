package school.sptech.neosspringjava.api.controllers.dashboardController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.service.dashboardService.DashboardService;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/totalGanho")
    public ResponseEntity<TotalGainDto> totalGain(@RequestBody TotalGainRequest request) {

        if (request.establishment() != null && request.start() != null && request.end() != null) {

            List<Employee> employeeList = employeeRepository.findAllByEstablishmentId(request.establishment());


            TotalGainDto totalGain = dashboardService.totalGain(employeeList, request.start(), request.end());

            if (totalGain.getValue() == null || totalGain.getValue() == 0.0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(totalGain);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
