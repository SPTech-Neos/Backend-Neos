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
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
import school.sptech.neosspringjava.service.marketService.MarketService;
import school.sptech.neosspringjava.service.schedulingService.SchedulingService;
import school.sptech.neosspringjava.service.statusService.StatusService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final EstablishmentRepository establishmentRepository;
    private final MarketService mService;
    private final SchedulingService sService;
    private final StatusService statusService;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {

        Payment payment = paymentRepository.save(
                Payment.builder()
                        .datePayment(paymentRequest.datePayment())
                        .market(mService.findById(paymentRequest.fkMarket()))
                        .schedule(sService.findById(paymentRequest.fkSchedule()))
                        .status(statusService.findById(paymentRequest.fkStatus()))
                        .build()
        );

        return new PaymentResponse(
                payment.getId(),
                payment.getDatePayment(),
                payment.getSchedule(),
                payment.getMarket(),
                payment.getStatus()
        );
    }

    public Payment findById(Integer id) {
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return payment;
    }

    public void delete(Integer id) {
        paymentRepository.deleteById(id);
    }

    public PaymentResponse update(Integer id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setDatePayment(paymentRequest.datePayment());
        payment.setMarket(mService.findById(paymentRequest.fkMarket()));
        payment.setSchedule(sService.findById(paymentRequest.fkSchedule()));
        payment.setStatus(statusService.findById(paymentRequest.fkStatus()));

        Payment updatedPayment = paymentRepository.save(payment);

        return new PaymentResponse(
                payment.getId(),
                payment.getDatePayment(),
                payment.getSchedule(),
                payment.getMarket(),
                payment.getStatus()
        );
    }


    public List<Payment> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return payments;
    }

        

//    public List<PaymentResponse> findAllByEstablishment(Establishment establishment, LocalDateTime initialDate) {
//
//
//        List<Payment> payments = paymentRepository.findAllByEstablishmentAndDateTimeGreaterThanEqualOrderByDateTimeDesc(establishment, initialDate);
//
//        return payments.stream()
//                .map(payment -> new PaymentResponse(
//                        payment.getId(),
//                        payment.getDatePayment(),
//                        payment.getSchedule(),
//                        payment.getMarket(),
//                        payment.getStatus()
//                ))
//                .toList();
//    }
//
//    public List<PaymentResponse> getPaymentsByClientId(Integer clientId) {
//        List<Payment> paymentsEntity = paymentRepository.findByClientId(clientId);
//        List<PaymentResponse> payments = new ArrayList<>();
//
//        for (Payment payment : paymentsEntity) {
//            PaymentResponse paymentResponse = new PaymentResponse(
//                    payment.getId(),
//                    payment.getDatePayment(),
//                    payment.getSchedule(),
//                    payment.getMarket(),
//                    payment.getStatus()
//            );
//            payments.add(paymentResponse);
//        }
//
//        return payments;
//    }
//
//    public List<PaymentResponse> getPaymentsByEstablishmentId(Integer establishmentId) {
//        List<Payment> paymentsEntity = paymentRepository.findByEstablishmentId(establishmentId);
//        List<PaymentResponse> payments = new ArrayList<>();
//
//        for (Payment payment : paymentsEntity) {
//            PaymentResponse paymentResponse = new PaymentResponse(
//                    payment.getId(),
//                    payment.getDatePayment(),
//                    payment.getSchedule(),
//                    payment.getMarket(),
//                    payment.getStatus()
//            );
//            payments.add(paymentResponse);
//        }
//
//        return payments;
//    }

    
}