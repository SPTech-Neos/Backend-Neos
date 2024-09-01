package school.sptech.neosspringjava.service.paymentService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.api.mappers.paymentMapper.PaymentMapper;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.of(paymentRequest);
        System.out.println(payment.getDatePayment());
        payment = paymentRepository.save(payment);

        return PaymentMapper.toResponse(payment);
    }

    public PaymentResponse findById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return PaymentMapper.toResponse(payment);
    }

    public void delete(Integer id) {
        paymentRepository.deleteById(id);
    }

    public PaymentResponse update(Integer id, PaymentRequest paymentRequest) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Payment payment = paymentMapper.of(paymentRequest);
        payment.setId(id);
        Payment updatedPayment = paymentRepository.save(payment);

        return PaymentMapper.toResponse(updatedPayment);
    }

    public List<PaymentResponse> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return PaymentMapper.toResponse(payments);
    }
}
