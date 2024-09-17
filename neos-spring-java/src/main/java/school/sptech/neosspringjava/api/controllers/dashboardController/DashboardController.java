package school.sptech.neosspringjava.api.controllers.dashboardController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketRquest;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeStats;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketPurchasedDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.service.dashboardService.DashboardService;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/totalGain")
    public ResponseEntity<TotalGainDto> totalGain(@RequestBody TotalGainRequest request) {
        if (request.establishment() != null && request.start() != null && request.end() != null) {
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

            if (qttStatus.quantity() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(qttStatus);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/leastPurchased")
    public ResponseEntity<MarketPurchasedDto> leastPurchased(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null) {
            MarketPurchasedDto marketPurchasedDto = dashboardService.leastPurchased(request.establishmentId());

            if (marketPurchasedDto.quantity() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketPurchasedDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/mostPurchased")
    public ResponseEntity<MarketPurchasedDto> mostPurchased(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null) {
            MarketPurchasedDto marketPurchasedDto = dashboardService.mostPurchased(request.establishmentId());

            if (marketPurchasedDto.quantity() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketPurchasedDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/leastProfitable")
    public ResponseEntity<MarketProfitableDto> leastProfitable(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null) {
            MarketProfitableDto marketProfitableDto = dashboardService.leastProfitable(request.establishmentId());

            if (marketProfitableDto.price() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketProfitableDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/mostProfitable")
    public ResponseEntity<MarketProfitableDto> mostProfitable(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null) {
            MarketProfitableDto marketProfitableDto = dashboardService.mostProfitable(request.establishmentId());

            if (marketProfitableDto.price() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketProfitableDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/countMarket")
    public ResponseEntity<Integer> countMarket(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            Integer response = dashboardService.countMarket(request.establishmentId(), request.start(), request.end());

            return ResponseEntity.ok(response != null ? response : 0);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/countMarketCanceled")
    public ResponseEntity<Integer> countMarketCanceled(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            Integer response = dashboardService.countMarketCanceled(request.establishmentId(), request.start(), request.end());

            return ResponseEntity.ok(response != null ? response : 0);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/employeeStats")
    public ResponseEntity<List<EmployeeStats>> employeeStats(@RequestBody TotalGainRequest request) {
        if (request.establishment() != null && request.start() != null && request.end() != null) {
            List<EmployeeStats> employeeStatsList = dashboardService.employeeStats(request.establishment(), request.start(), request.end());

            if (employeeStatsList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(employeeStatsList);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    
    @GetMapping("/graficCountMarket/{period}")
    public ResponseEntity<List<MarketStatsDTO>> graficCountMarket(@RequestBody MarketRquest request, @PathVariable Integer period) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = new ArrayList<>(); 
        
            if(period == 1){
            stats = dashboardService.getDailyOrderStats(request.establishmentId(), request.start(), request.end());
            }else 
            if(period == 2 ){
                stats = dashboardService.getWeeklyOrderStats(request.establishmentId(), request.start(), request.end());
            }else
            if(period == 3){
                stats = dashboardService.getMonthlyOrderStats(request.establishmentId(), request.start(), request.end());
            }

            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(stats);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }






    @GetMapping("/dailyOrderStats")
    public ResponseEntity<List<MarketStatsDTO>> getDailyOrderStats(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = dashboardService.getDailyOrderStats(request.establishmentId(), request.start(), request.end());

            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(stats);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/weeklyOrderStats")
    public ResponseEntity<List<MarketStatsDTO>> getWeeklyOrderStats(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = dashboardService.getWeeklyOrderStats(request.establishmentId(), request.start(), request.end());

            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(stats);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/monthlyOrderStats")
    public ResponseEntity<List<MarketStatsDTO>> getMonthlyOrderStats(@RequestBody MarketRquest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = dashboardService.getMonthlyOrderStats(request.establishmentId(), request.start(), request.end());

            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(stats);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
