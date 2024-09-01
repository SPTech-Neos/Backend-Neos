package school.sptech.neosspringjava.api.controllers.dashboardController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketRquest;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainRequest;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketPurchasedDto;
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

    @GetMapping("/totalGain")
    public ResponseEntity<TotalGainDto> totalGain(@RequestBody TotalGainRequest request) {

        if (request.establishment() != null && request.start() != null && request.end() != null) {

            //List<Employee> employeeList = employeeRepository.findAllByEstablishmentId(request.establishment()); 


            TotalGainDto totalGain = dashboardService.totalGain(request.establishment(), request.start(), request.end());

            if (totalGain.getValue() == null || totalGain.getValue() == 0.0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(totalGain);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/quantityStatus")
    public ResponseEntity<QuantityStatusDto> quantityStatus(@RequestBody TotalGainRequest request) {

            if (request.establishment() != null && request.start() != null && request.end() != null) {

    
                QuantityStatusDto qttStatus = dashboardService.quantityStatus(request.establishment(), request.start(), request.end());
    
                if (qttStatus.quantity()==null) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                } else {
                    return ResponseEntity.ok(qttStatus);
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
    }
    @GetMapping("/leastPurchased")
    public ResponseEntity<MarketPurchasedDto> leastPurchased(@RequestBody MarketRquest request){
        if (request.establishmentId() != null) {

    
            MarketPurchasedDto marketPurchasedDto = dashboardService.leastPurchased(request.establishmentId());

            if (marketPurchasedDto.quantity()==null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketPurchasedDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/mostPurchased")
    public ResponseEntity<MarketPurchasedDto> mostPurchased(@RequestBody MarketRquest request){
        if (request.establishmentId() != null) {

    
            MarketPurchasedDto marketPurchasedDto = dashboardService.mostPurchased(request.establishmentId());

            if (marketPurchasedDto.quantity()==null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketPurchasedDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/leastProfitable")
    public ResponseEntity<MarketProfitableDto> leastProfitable(@RequestBody MarketRquest request){
        if (request.establishmentId() != null) {

    
            MarketProfitableDto marketProfitableDto = dashboardService.leastProfitable(request.establishmentId());

            if (marketProfitableDto.price()==null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketProfitableDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/mostProfitable")
    public ResponseEntity<MarketProfitableDto> mostProfitable(@RequestBody MarketRquest request){
        if (request.establishmentId() != null) {

    
            MarketProfitableDto marketProfitableDto = dashboardService.mostProfitable(request.establishmentId());

            if (marketProfitableDto.price()==null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketProfitableDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
