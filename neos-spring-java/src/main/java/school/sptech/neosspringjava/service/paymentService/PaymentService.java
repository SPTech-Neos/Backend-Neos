package school.sptech.neosspringjava.service.paymentService;

import org.springframework.http.HttpStatus;
// PaymentService.java
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;
import school.sptech.neosspringjava.service.marketService.MarketService;
import school.sptech.neosspringjava.service.schedulingService.SchedulingService;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;
    private final MarketService mService;
    private final SchedulingService sService;
    private final StatusService statusService;

    private void verifyEstablishmentExists(Integer establishmentId) {
        if (!establishmentRepository.existsById(establishmentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
    }

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found"));

        return payment;
    }

    public void delete(Integer id) {
        paymentRepository.deleteById(id);
    }

    public PaymentResponse update(Integer id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found"));

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

    public Double getTotalRentByEstablishment(Integer id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Establishment not found"));

        List<Payment> payments = paymentRepository.findAllByEstablishment(establishment.getId());

        Double totalRent = 0.0;

        for (Payment payment : payments) {
            totalRent += payment.getSchedule().getService().getPrice();
        }

        return totalRent;

        
    }


    public Double getTotalRentByEstablishmentAndEmployee(Integer id, Integer idEmployye) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Establishment not found"));

                Employee employee = employeeRepository.findById(idEmployye).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));

        List<Payment> payments = paymentRepository.findAllByEstablishmentAndEmployee(establishment.getId(), employee.getId());

        Double totalRent = 0.0;

        for (Payment payment : payments) {
            totalRent += payment.getSchedule().getService().getPrice();
        }

        return totalRent;
    }

    public Double getTotalRentByEstablishmentAndEmployeeAndStartDate(Integer id, Integer idEmployye, String startDate) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Establishment not found"));

                Employee employee = employeeRepository.findById(idEmployye).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));
                

                if(startDate == null || !startDate.contains("T")){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data com formatação incorreta (YYYY-MM-ddTHH:mm:ss)");
                }

                LocalDateTime start = LocalDateTime.parse(startDate);
        
        
        List<Payment> payments = paymentRepository.findAllByEstablishmentAndEmployeeAndStartDate(establishment, employee, start);

        Double totalRent = 0.0;

        for (Payment payment : payments) {
            totalRent += payment.getSchedule().getService().getPrice();
        }

        return totalRent;
    }

    public Double getTotalRentByEstablishmentAndEmployeeAndEndDate(Integer id, Integer idEmployye, String endDate) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Establishment not found"));

                Employee employee = employeeRepository.findById(idEmployye).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));


                if(endDate == null || !endDate.contains("T")){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data com formatação incorreta (YYYY-MM-ddTHH:mm:ss)");

                }

                LocalDateTime end = LocalDateTime.parse(endDate);

        List<Payment> payments = paymentRepository.findAllByEstablishmentAndEmployeeAndEndDate(establishment, employee,end);

        Double totalRent = 0.0;

        for (Payment payment : payments) {
            totalRent += payment.getSchedule().getService().getPrice();
        }

        return totalRent;
    }
   


    
}