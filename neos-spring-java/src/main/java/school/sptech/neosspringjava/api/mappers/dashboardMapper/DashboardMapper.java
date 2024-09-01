package school.sptech.neosspringjava.api.mappers.dashboardMapper;

import java.util.List;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.domain.model.payment.Payment;

@Component
public class DashboardMapper {

    public TotalGainDto totalGain(List<Payment> payments) {

        TotalGainDto totalGain = new TotalGainDto();
        totalGain.setValue(0.0); // Inicializar o valor total

        for (Payment payment : payments) {
            if (totalGain.getDateTimeStart() == null || totalGain.getDateTimeStart().isBefore(payment.getDatePayment())) {
                totalGain.setDateTimeStart(payment.getDatePayment());
            }
            if (totalGain.getDateTimeEnd() == null || totalGain.getDateTimeEnd().isAfter(payment.getDatePayment())) {
                totalGain.setDateTimeEnd(payment.getDatePayment());
            }
            totalGain.setValue(totalGain.getValue() + payment.getMarket().getProduct().getPrice());
        }

        return totalGain;
    }

    public QuantityStatusDto quantityStatus(Payment payment){
        System.out.println(payment.getMarket().getQuantity());
        return new QuantityStatusDto(payment.getMarket().getQuantity(), payment.getStatus().getName());
    }
}
