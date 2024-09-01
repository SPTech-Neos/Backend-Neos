package school.sptech.neosspringjava.service.dashboardService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.api.mappers.dashboardMapper.DashboardMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PaymentRepository paymentRepository;
    private final DashboardMapper dashboardMapper;

    public TotalGainDto totalGain(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {

        List<Payment> listPayment = new ArrayList<>();
        listPayment.addAll(paymentRepository.findPaymentsByDateRangeAndEstablishment(dateStart, dateEnd, establishment));
        

        if (listPayment == null || listPayment.isEmpty()) {
            return new TotalGainDto(); // Retorna um TotalGainDto vazio se não houver pagamentos
        } else {
            return dashboardMapper.totalGain(listPayment);
        }
    }

    public QuantityStatusDto quantityStatus(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd){
        List<Payment> listPayment = new ArrayList<>();
        listPayment.addAll(paymentRepository.findMarketQuantityAndStatusNameByDateRangeAndEstablishment(dateStart,dateEnd,establishment));

        Payment paymentX = new Payment();
        for (Payment payment : listPayment) {
            if (paymentX.getDatePayment() == null) {
                paymentX = payment;
            }else
            if (payment.getDatePayment().isAfter(paymentX.getDatePayment())&& payment.getStatus().getName() == "Aguardando Pagamento" || payment.getStatus().getName() == "Aguardando Pagamento" && paymentX.getStatus().getName() != "Aguardando Pagamento") {
                paymentX = payment;
            }
        }
        
        return dashboardMapper.quantityStatus(paymentX);
        
    }
}
