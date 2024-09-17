package school.sptech.neosspringjava.service.dashboardService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeStats;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketPurchasedDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.api.mappers.dashboardMapper.DashboardMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.market.Market;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.marketRepository.MarketRepository;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PaymentRepository paymentRepository;
    private final MarketRepository marketRepository;
    private final DashboardMapper dashboardMapper;
    private final EmployeeRepository employeeRepository;
    private final SchedulingRepository schedulingRepository;

    public TotalGainDto totalGain(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {

        List<Payment> listPayment = new ArrayList<>();
        listPayment
                .addAll(paymentRepository.findPaymentsByDateRangeAndEstablishment(dateStart, dateEnd, establishment));
System.out.println(listPayment.get(0));
        if (listPayment == null || listPayment.isEmpty()) {
            return new TotalGainDto(); // Retorna um TotalGainDto vazio se não houver pagamentos
        } else {
            return dashboardMapper.totalGain(listPayment);
        }
    }

    public QuantityStatusDto quantityStatus(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<Payment> listPayment = new ArrayList<>();
        listPayment.addAll(paymentRepository.findMarketQuantityAndStatusNameByDateRangeAndEstablishment(dateStart,
                dateEnd, establishment));

        Payment paymentX = new Payment();
        for (Payment payment : listPayment) {
            if (paymentX.getDatePayment() == null) {
                paymentX = payment;
            } else if (payment.getDatePayment().isAfter(paymentX.getDatePayment())
                    && payment.getStatus().getName() == "Aguardando Pagamento"
                    || payment.getStatus().getName() == "Aguardando Pagamento"
                            && paymentX.getStatus().getName() != "Aguardando Pagamento") {
                paymentX = payment;
            }
        }

        return dashboardMapper.quantityStatus(paymentX);

    }

    public MarketPurchasedDto mostPurchased(Integer establishment) {
        return dashboardMapper.mostPurchased(marketRepository.findAggregatedMarketDataByEstablishment(establishment));
    }

    public MarketPurchasedDto leastPurchased(Integer establishment) {
        return dashboardMapper.leastPurchased(marketRepository.findAggregatedMarketDataByEstablishment(establishment));
    }

    public MarketProfitableDto mostProfitable(Integer establishment) {
        return dashboardMapper.mostProfitable(marketRepository.findTotalSalesValueByProduct(establishment));
    }

    public MarketProfitableDto leastProfitable(Integer establishment) {
        return dashboardMapper.leastProfitable(marketRepository.findTotalSalesValueByProduct(establishment));
    }

    public Integer countMarket(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
        return marketRepository.countMarketsByEstablishmentAndOrderStatus(establishment, dateStart, dateEnd);
    }

    public Integer countMarketCanceled(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
        return marketRepository.countMarketsByEstablishmentAndOrderStatusCanceled(establishment, dateStart, dateEnd);
    }

    public List<EmployeeStats> employeeStats(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
        return dashboardMapper
                .toEmployeeStatsList(schedulingRepository.findEmployeeStats(establishment, dateStart, dateEnd));
    }

    public List<MarketStatsDTO> getDailyOrderStats(Integer establishmentId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return dashboardMapper.mapToMarketStatsDTOList(marketRepository.findDailyOrderStats(establishmentId, startDate, endDate));
    }

    public List<MarketStatsDTO> getWeeklyOrderStats(Integer establishmentId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return dashboardMapper.mapToMarketStatsDTOList(marketRepository.findWeeklyOrderStats(establishmentId, startDate, endDate));
    }

    public List<MarketStatsDTO> getMonthlyOrderStats(Integer establishmentId, LocalDateTime startDate,
            LocalDateTime endDate) {
        return dashboardMapper.mapToMarketStatsDTOList(marketRepository.findMonthlyOrderStats(establishmentId, startDate, endDate));
    }

}
