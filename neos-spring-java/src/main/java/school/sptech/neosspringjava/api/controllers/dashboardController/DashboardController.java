package school.sptech.neosspringjava.api.controllers.dashboardController;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainRequest;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGanhoRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.api.mappers.clientMapper.ClientMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.service.dashboardService.DashboardService;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;


    @GetMapping("/totalGanho")
    public ResponseEntity<TotalGainDto> totalGain(@RequestBody TotalGainRequest request){

        if (request.establishment()!= null) {
            TotalGainDto pay = dashboardService.totalGain(request.establishment(), request.start(), request.end());
            
            if ( pay == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else{
                return ResponseEntity.ok().body(pay);
            }
            
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    public void statusQuantidadeDePedido(){

    }

}
