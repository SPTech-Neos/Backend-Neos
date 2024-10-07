package school.sptech.neosspringjava.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.dashboardDto.EmployeeStats;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.MarketPurchasedDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.dashboardDto.TotalGainDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketStatsDTO;
import school.sptech.neosspringjava.domain.model.Payment;

@Component
public class DashboardMapper {

    public TotalGainDto totalGain(List<Payment> payments) {
        TotalGainDto totalGain = new TotalGainDto();
        totalGain.setValue(0.0); // Inicializa o valor total

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

    public QuantityStatusDto quantityStatus(Payment payment) {
        return new QuantityStatusDto(payment.getMarket().getQuantity(), payment.getStatus().getName());
    }

    public MarketPurchasedDto mostPurchased(List<Object[]> objs) {
        if (objs.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }
        Object[] obj = objs.get(0);
        
        Integer purchasedCount;
        try {
            purchasedCount = Integer.parseInt(obj[1].toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O valor de compra não é um número válido: " + obj[1], e);
        }
        
        if (!(obj[0] instanceof String)) {
            throw new IllegalArgumentException("O nome do produto não é uma string válida.");
        }
        
        return new MarketPurchasedDto(purchasedCount, (String) obj[0]);
    }
    
    public MarketPurchasedDto leastPurchased(List<Object[]> objs) {
        if (objs.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }
        Object[] obj = objs.get(objs.size() - 1);
        
        Integer purchasedCount;
        try {
            purchasedCount = Integer.parseInt(obj[1].toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O valor de compra não é um número válido: " + obj[1], e);
        }

        if (!(obj[0] instanceof String)) {
            throw new IllegalArgumentException("O nome do produto não é uma string válida.");
        }
        
        return new MarketPurchasedDto(purchasedCount, (String) obj[0]);
    }

    public MarketProfitableDto mostProfitable(List<Object[]> objs) {
        if (objs.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }
        Object[] obj = objs.get(0);
        
        Double profit;
        try {
            profit = Double.parseDouble(obj[1].toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O valor de lucro não é um número válido: " + obj[1], e);
        }

        if (!(obj[0] instanceof String)) {
            throw new IllegalArgumentException("O nome do produto não é uma string válida.");
        }
        
        return new MarketProfitableDto((String) obj[0], profit);
    }
    
    public MarketProfitableDto leastProfitable(List<Object[]> objs) {
        if (objs.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }
        Object[] obj = objs.get(objs.size() - 1);
        
        Double profit;
        try {
            profit = Double.parseDouble(obj[1].toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O valor de lucro não é um número válido: " + obj[1], e);
        }

        if (!(obj[0] instanceof String)) {
            throw new IllegalArgumentException("O nome do produto não é uma string válida.");
        }
        
        return new MarketProfitableDto((String) obj[0], profit);
    }

    // Novo método para converter List<Object[]> em List<EmployeeStats>
    public List<EmployeeStats> toEmployeeStatsList(List<Object[]> objs) {
        return objs.stream().map(obj -> {
            if (obj.length != 5) {
                throw new IllegalArgumentException("Cada Object[] deve ter exatamente 5 elementos.");
            }
            
            String imgUrl = (String) obj[0];
            String name = (String) obj[1];
            Double mediaAvaliation;
            Integer totalHoursSpent;
            Double totalValue;
            
            try {
                mediaAvaliation = Double.parseDouble(obj[2].toString());
                totalHoursSpent = Integer.parseInt(obj[3].toString());
                totalValue = Double.parseDouble(obj[4].toString());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Um dos valores não é numérico: " + e.getMessage(), e);
            }

            return new EmployeeStats(imgUrl, name, mediaAvaliation, totalHoursSpent, totalValue);
        }).collect(Collectors.toList());
    }

        public MarketStatsDTO mapToMarketStatsDTO(Object[] row) {
        // Supondo que o formato do array é [period, canceledOrders, totalOrders]
        String period = row[0] != null ? row[0].toString() : null;
        Integer canceledOrders = row[1] != null ? ((Number) row[1]).intValue() : 0;
        Integer totalOrders = row[2] != null ? ((Number) row[2]).intValue() : 0;

        return MarketStatsDTO.builder()
                             .period(period)
                             .canceledOrders(canceledOrders)
                             .totalOrders(totalOrders)
                             .build();
    }
    public List<MarketStatsDTO> mapToMarketStatsDTOList(List<Object[]> rows) {
        return rows.stream()
                   .map(this::mapToMarketStatsDTO)
                   .collect(Collectors.toList());
    }
}
