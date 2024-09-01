package school.sptech.neosspringjava.api.mappers.dashboardMapper;

import java.util.List;
import org.springframework.stereotype.Component;
import school.sptech.neosspringjava.api.dtos.dashboardDto.QuantityStatusDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketProfitableDto;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketPurchasedDto;
import school.sptech.neosspringjava.api.dtos.paymentDto.TotalGainDto;
import school.sptech.neosspringjava.domain.model.payment.Payment;

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
        System.out.println(payment.getMarket().getQuantity());
        return new QuantityStatusDto(payment.getMarket().getQuantity(), payment.getStatus().getName());
    }

    public MarketPurchasedDto mostPurchased(List<Object[]> objs) {
        if (objs.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }
        Object[] obj = objs.get(0);
        System.out.println(obj[0]);
        System.out.println(obj[1]);
        
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
        System.out.println(obj[0]);
        System.out.println(obj[1]);
        
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
}
