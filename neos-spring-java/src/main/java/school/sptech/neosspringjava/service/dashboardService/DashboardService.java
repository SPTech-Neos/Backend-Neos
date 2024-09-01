package school.sptech.neosspringjava.service.dashboardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.api.mappers.dashboardMapper.DashboardMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PaymentRepository paymentRepository;
    private final DashboardMapper dashboardMapper;

    public TotalGainDto totalGain(Establishment establishment , LocalDateTime dateStart, LocalDateTime dateEnd){

        List<Payment> listPayment = paymentRepository.findByIdAndDateTimeBetweenOrderByDateTimeDesc(establishment.getId(), dateStart, dateEnd);
        if (listPayment == null) {
            return null;
        }else{
            return dashboardMapper.totalGain(listPayment);
        }

       
    }
}
