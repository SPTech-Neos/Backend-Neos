package school.sptech.neosspringjava.api.mappers;

import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.domain.model.Payment;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {
    public static PaymentResponse toResponse(Payment p){
        return new PaymentResponse(
                p.getId(),
                p.getDatePayment(),
                p.getSchedule(),
                p.getMarket(),
                p.getStatus()
        );
    }

    public static List<PaymentResponse> toResponse(List<Payment> payments){
        return payments.stream().map(PaymentMapper::toResponse).collect(Collectors.toList());
    }
}
