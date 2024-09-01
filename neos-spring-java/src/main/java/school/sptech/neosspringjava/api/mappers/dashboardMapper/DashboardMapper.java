package school.sptech.neosspringjava.api.mappers.dashboardMapper;

import java.util.List;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.domain.model.payment.Payment;
@Component
public class DashboardMapper {

    public TotalGainDto totalGain(List<Payment>payments){
        
        TotalGainDto totalGain = new TotalGainDto();

        for (Payment payment : payments) {
            if (totalGain.getDateTimeStart() == null || totalGain.getDateTimeStart().isBefore(payment.getDateTime())) {
                totalGain.setDateTimeStart(payment.getDateTime());
            }else if(totalGain.getDateTimeEnd() == null || totalGain.getDateTimeEnd().isAfter(payment.getDateTime())){
                totalGain.setDateTimeEnd(payment.getDateTime());
            }
            totalGain.setValue(totalGain.getValue()+payment.getValue());
        }

        return totalGain;
    }


}
