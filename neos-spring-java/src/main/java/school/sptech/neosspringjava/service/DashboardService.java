package school.sptech.neosspringjava.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.dashboardDto.EmployeeStats;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketPurchasedDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.api.mappers.DashboardMapper;
import school.sptech.neosspringjava.domain.model.Payment;
import school.sptech.neosspringjava.domain.repository.*;

@Service
@RequiredArgsConstructor
public class DashboardService { 

    private final PaymentRepository paymentRepository;
    private final MarketRepository marketRepository;
    private final DashboardMapper dashboardMapper;
    private final EmployeeRepository employeeRepository;
    private final SchedulingRepository schedulingRepository;
    private final EstablishmentRepository establishmentRepository;


    private void verifyEstablishmentExists(Integer establishmentId) {
        if (!establishmentRepository.existsById(establishmentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
    }

    public TotalGainDto totalGain(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {

        List<Payment> listPayment = new ArrayList<>();
        listPayment.addAll(paymentRepository.findPaymentsByDateRangeAndEstablishment(dateStart, dateEnd, establishment));
        if (listPayment == null || listPayment.isEmpty()) {
        TotalGainDto totalgain = new TotalGainDto();
        totalgain.setDateTimeStart(dateStart);
        totalgain.setDateTimeEnd(dateEnd);
        totalgain.setValue(0.0);
        return totalgain;
        } else {
            return dashboardMapper.totalGain(listPayment);
        }
    }

    public QuantityStatusDto quantityStatus(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<Payment> listPayment = new ArrayList<>();
        listPayment.addAll(paymentRepository.findMarketQuantityAndStatusNameByDateRangeAndEstablishment(dateStart,dateEnd, establishment));
        if (listPayment != null && !listPayment.isEmpty()) {
            
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
        }else{
            
            return new QuantityStatusDto(null,"Sem Pagamentos"); 
        }

    }
    
public MarketPurchasedDto mostPurchased(Integer establishment, LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishment);
    var marketData = marketRepository.findAggregatedMarketDataByEstablishment(establishment, startDate, endDate);
    if (marketData == null || marketData.isEmpty()) {
        return new MarketPurchasedDto(null, null);
    }
    return dashboardMapper.mostPurchased(marketData);
}

public MarketPurchasedDto leastPurchased(Integer establishment,  LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishment);
    var marketData = marketRepository.findAggregatedMarketDataByEstablishment(establishment, startDate, endDate);
    if (marketData == null || marketData.isEmpty()) {
        return new MarketPurchasedDto(null, null);
    }
    return dashboardMapper.leastPurchased(marketData);
}

public MarketProfitableDto mostProfitable(Integer establishment, LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishment);
    var salesData = marketRepository.findTotalSalesValueByProduct(establishment, startDate, endDate);
    if (salesData == null || salesData.isEmpty()) {
        return new MarketProfitableDto(null, null);
    }
    return dashboardMapper.mostProfitable(salesData);
}

public MarketProfitableDto leastProfitable(Integer establishment, LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishment);
    var salesData = marketRepository.findTotalSalesValueByProduct(establishment, startDate, endDate);
    if (salesData == null || salesData.isEmpty()) {
        return new MarketProfitableDto(null, null);
    }
    return dashboardMapper.leastProfitable(salesData);
}

public Integer countMarket(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
    verifyEstablishmentExists(establishment);
    return marketRepository.countMarketsByEstablishmentAndOrderStatus(establishment, dateStart, dateEnd);
}

public Integer countMarketCanceled(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
    verifyEstablishmentExists(establishment);
    return marketRepository.countMarketsByEstablishmentAndOrderStatusCanceled(establishment, dateStart, dateEnd);
}

public List<EmployeeStats> employeeStats(Integer establishment, LocalDateTime dateStart, LocalDateTime dateEnd) {
    verifyEstablishmentExists(establishment);
    List<Object[]> employeeStatsData = schedulingRepository.findEmployeeStats(establishment, dateStart, dateEnd);
    if (employeeStatsData == null || employeeStatsData.isEmpty()) {
        List<EmployeeStats> l = new ArrayList<>();
        return l; 
    }
    return dashboardMapper.toEmployeeStatsList(employeeStatsData);
}

public List<MarketStatsDTO> getDailyOrderStats(Integer establishmentId, LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishmentId);
    List<Object[]> orderStats = marketRepository.findDailyOrderStats(establishmentId, startDate, endDate);
    if (orderStats == null || orderStats.isEmpty()) {
        List<MarketStatsDTO> l = new ArrayList<>();
        return l; 
    }
    return dashboardMapper.mapToMarketStatsDTOList(orderStats);
}

public List<MarketStatsDTO> getWeeklyOrderStats(Integer establishmentId, LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishmentId);
    List<Object[]> orderStats = marketRepository.findWeeklyOrderStats(establishmentId, startDate, endDate);
    if (orderStats == null || orderStats.isEmpty()) {
        List<MarketStatsDTO> l = new ArrayList<>();
        return l; 
    }
    return dashboardMapper.mapToMarketStatsDTOList(orderStats);
}

public List<MarketStatsDTO> getMonthlyOrderStats(Integer establishmentId, LocalDateTime startDate, LocalDateTime endDate) {
    verifyEstablishmentExists(establishmentId);
    List<Object[]> orderStats = marketRepository.findMonthlyOrderStats(establishmentId, startDate, endDate);
    if (orderStats == null || orderStats.isEmpty()) {
        List<MarketStatsDTO> l = new ArrayList<>();
        return l; 
    }
    return dashboardMapper.mapToMarketStatsDTOList(orderStats);
}
}