package school.sptech.neosspringjava.api.mappers.paymentMapper;

import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.status.Status;
import school.sptech.neosspringjava.domain.repository.orderRepositiory.OrderRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.status.StatusRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class PaymentMapper {
    
    private final SchedulingRepository schedulingRepository;
    
    private final OrderRepository orderRepository;
    private final StatusRepository statusRepository;



    public static PaymentResponse toResponse(Payment p){
        return new PaymentResponse(
                p.getId(),
                p.getDatePayment(),
                p.getTotal(),
                p.getSchedule(),
                p.getOrder(),
                p.getStatus()
        );
    }

    public static List<PaymentResponse> toResponse(List<Payment> payments){
        return payments.stream().map(PaymentMapper::toResponse).collect(Collectors.toList());
    }

    public Payment of(PaymentRequest paymentRequest) {
        System.out.println("maper"+paymentRequest.datePayment());
        Payment payment = new Payment();
        payment.setDatePayment(paymentRequest.datePayment());
        payment.setTotal(paymentRequest.total());

        Scheduling schedule = schedulingRepository.findById(paymentRequest.fkSchedule())
                .orElseThrow(() -> new RuntimeException("Scheduling not found"));
        payment.setSchedule(schedule);

        Order order = orderRepository.findById(paymentRequest.fkOrder())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        payment.setOrder(order);

        Status status = statusRepository.findById(paymentRequest.fkStatus())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        payment.setStatus(status);

        return payment;
    }
}
