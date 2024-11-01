package school.sptech.neosspringjava.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.DashboardRequest;
import school.sptech.neosspringjava.api.dtos.dashboardDto.EmployeeStats;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketPurchasedDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.service.DashboardService;
 

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final EmployeeRepository employeeRepository;

    @PostMapping("/totalGain")
    public ResponseEntity<TotalGainDto> totalGain(@RequestBody DashboardRequest request) {

        if (request.establishmentId() != null && request.start() != null && request.end() != null) {

            // List<Employee> employeeList = employeeRepository.findAllByEstablishmentId(request.establishment());


            TotalGainDto totalGain = dashboardService.totalGain(request.establishmentId(), request.start(), request.end());

            if (totalGain.getValue() == null || totalGain.getValue() == 0.0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(totalGain);
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/quantityStatus")
    public ResponseEntity<QuantityStatusDto> quantityStatus(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            QuantityStatusDto qttStatus = dashboardService.quantityStatus(request.establishmentId(), request.start(),request.end());

            if (qttStatus.quantity() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(qttStatus);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/leastPurchased")
    public ResponseEntity<MarketPurchasedDto> leastPurchased(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null) {
            MarketPurchasedDto marketPurchasedDto = dashboardService.leastPurchased(request.establishmentId(), request.start(), request.end());

            if (marketPurchasedDto.quantity() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketPurchasedDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/mostPurchased")
    public ResponseEntity<MarketPurchasedDto> mostPurchased(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null) {
            MarketPurchasedDto marketPurchasedDto = dashboardService.mostPurchased(request.establishmentId(), request.start(), request.end());

            if (marketPurchasedDto.quantity() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketPurchasedDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/leastProfitable")
    public ResponseEntity<MarketProfitableDto> leastProfitable(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null) {
            MarketProfitableDto marketProfitableDto = dashboardService.leastProfitable(request.establishmentId(), request.start(), request.end());

            if (marketProfitableDto.price() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketProfitableDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/mostProfitable")
    public ResponseEntity<MarketProfitableDto> mostProfitable(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null) {
            MarketProfitableDto marketProfitableDto = dashboardService.mostProfitable(request.establishmentId(), request.start(), request.end());

            if (marketProfitableDto.price() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(marketProfitableDto);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/countMarket")
    public ResponseEntity<Integer> countMarket(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            Integer response = dashboardService.countMarket(request.establishmentId(), request.start(), request.end());

            return ResponseEntity.ok(response != null ? response : 0);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/countMarketCanceled")
    public ResponseEntity<Integer> countMarketCanceled(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            Integer response = dashboardService.countMarketCanceled(request.establishmentId(), request.start(),
                    request.end());

            return ResponseEntity.ok(response != null ? response : 0);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/employeeStats")
    public ResponseEntity<List<EmployeeStats>> employeeStats(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<EmployeeStats> employeeStatsList = dashboardService.employeeStats(request.establishmentId(),
                    request.start(), request.end());

            if (employeeStatsList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(employeeStatsList);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/graficCountMarket/{period}")
    public ResponseEntity<List<MarketStatsDTO>> graficCountMarket(@RequestBody DashboardRequest request,
            @PathVariable Integer period) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = new ArrayList<>();

            if (period == 1) {
                stats = dashboardService.getDailyOrderStats(request.establishmentId(), request.start(), request.end());
            } else if (period == 2) {
                stats = dashboardService.getWeeklyOrderStats(request.establishmentId(), request.start(), request.end());
            } else if (period == 3) {
                stats = dashboardService.getMonthlyOrderStats(request.establishmentId(), request.start(),
                        request.end());
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

    @PostMapping("/dailyOrderStats")
    public ResponseEntity<List<MarketStatsDTO>> getDailyOrderStats(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = dashboardService.getDailyOrderStats(request.establishmentId(), request.start(),
                    request.end());

            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(stats);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/weeklyOrderStats")
    public ResponseEntity<List<MarketStatsDTO>> getWeeklyOrderStats(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = dashboardService.getWeeklyOrderStats(request.establishmentId(),
                    request.start(), request.end());

            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(stats);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/monthlyOrderStats")
    public ResponseEntity<List<MarketStatsDTO>> getMonthlyOrderStats(@RequestBody DashboardRequest request) {
        if (request.establishmentId() != null && request.start() != null && request.end() != null) {
            List<MarketStatsDTO> stats = dashboardService.getMonthlyOrderStats(request.establishmentId(),
                    request.start(), request.end());

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
