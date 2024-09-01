package school.sptech.neosspringjava.service.dashboardService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
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

    public TotalGainDto totalGain(List<Employee> employeeList, LocalDateTime dateStart, LocalDateTime dateEnd) {

        List<Payment> listPayment = new ArrayList<>();
        for (Employee employee : employeeList) {
        listPayment.addAll(paymentRepository.findByDatePaymentBetweenAndScheduleEmployeeIdOrderByDatePaymentDesc(dateStart, dateEnd, employee.getId()));
        }

        if (listPayment == null || listPayment.isEmpty()) {
            return new TotalGainDto(); // Retorna um TotalGainDto vazio se não houver pagamentos
        } else {
            return dashboardMapper.totalGain(listPayment);
        }
    }
}
