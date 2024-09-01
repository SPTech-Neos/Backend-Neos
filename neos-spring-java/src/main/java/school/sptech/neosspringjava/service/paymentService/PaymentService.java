package school.sptech.neosspringjava.service.paymentService;

// PaymentService.java
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.api.mappers.paymentMapper.PaymentMapper;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {


        Payment payment = paymentRepository.save(paymentMapper.of(paymentRequest));

        return new PaymentResponse(
                payment.getId(),
                payment.getDateTime(),
                payment.getValue(),
                payment.getProduct(),
                payment.getClient(),
                payment.getEstablishment()
        );
    }

    public PaymentResponse findById(Integer id) {
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return new PaymentResponse(
                payment.getId(),
                payment.getDateTime(),
                payment.getValue(),
                payment.getProduct(),
                payment.getClient(),
                payment.getEstablishment()
        );
    }

    public void delete(Integer id) {
        paymentRepository.deleteById(id);
    }

    public PaymentResponse update(Integer id, PaymentRequest paymentRequest) {

        Payment payment = paymentMapper.of(paymentRequest);
        payment.setId(id);
        Payment updatedPayment = paymentRepository.save(payment);

        return new PaymentResponse(
                updatedPayment.getId(),
                updatedPayment.getDateTime(),
                payment.getValue(),
                updatedPayment.getProduct(),
                updatedPayment.getClient(),
                updatedPayment.getEstablishment()
        );
    }


    public List<PaymentResponse> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> new PaymentResponse(
                        payment.getId(),
                        payment.getDateTime(),
                        payment.getValue(),
                        payment.getProduct(),
                        payment.getClient(),
                        payment.getEstablishment()
                ))
                .toList();
    }

        

    public List<PaymentResponse> findAllByEstablishment(Establishment establishment, LocalDateTime initialDate) {
       

        List<Payment> payments = paymentRepository.findAllByEstablishmentAndDateTimeGreaterThanEqualOrderByDateTimeDesc(establishment, initialDate);

        return payments.stream()
                .map(payment -> new PaymentResponse(
                        payment.getId(),
                        payment.getDateTime(),
                        payment.getValue(),
                        payment.getProduct(),
                        payment.getClient(),
                        payment.getEstablishment()
                ))
                .toList();
    }

    public List<PaymentResponse> getPaymentsByClientId(Integer clientId) {
        List<Payment> paymentsEntity = paymentRepository.findByClientId(clientId);
        List<PaymentResponse> payments = new ArrayList<>();

        for (Payment payment : paymentsEntity) {
            PaymentResponse paymentResponse = new PaymentResponse(
                    payment.getId(),
                    payment.getDateTime(),
                    payment.getValue(),
                    payment.getProduct(),
                    payment.getClient(),
                    payment.getEstablishment()
            );
            payments.add(paymentResponse);
        }

        return payments;
    }

    public List<PaymentResponse> getPaymentsByEstablishmentId(Integer establishmentId) {
        List<Payment> paymentsEntity = paymentRepository.findByEstablishmentId(establishmentId);
        List<PaymentResponse> payments = new ArrayList<>();

        for (Payment payment : paymentsEntity) {
            PaymentResponse paymentResponse = new PaymentResponse(
                    payment.getId(),
                    payment.getDateTime(),
                    payment.getValue(),
                    payment.getProduct(),
                    payment.getClient(),
                    payment.getEstablishment()
            );
            payments.add(paymentResponse);
        }

        return payments;
    }

    
}