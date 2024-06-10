package school.sptech.neosspringjava.service.paymentService;

// PaymentService.java
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final EstablishmentRopository establishmentRepository;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Product product = productRepository

                .findById(paymentRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Client client = clientRepository
                .findById(paymentRequest.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Establishment establishment = establishmentRepository
                .findById(paymentRequest.establishmentId())
                .orElseThrow(() -> new RuntimeException("Establishment not found"));

        Payment payment = paymentRepository.save(
                Payment.builder()
                        .dateTime(paymentRequest.dateTime())
                        .product(product)
                        .client(client)
                        .establishment(establishment)
                        .build()
        );

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
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Product product = productRepository
                .findById(paymentRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Client client = clientRepository
                .findById(paymentRequest.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Establishment establishment = establishmentRepository
                .findById(paymentRequest.establishmentId())
                .orElseThrow(() -> new RuntimeException("Establishment not found"));

        payment.setDateTime(paymentRequest.dateTime());
        payment.setProduct(product);
        payment.setClient(client);
        payment.setEstablishment(establishment);

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


}