package school.sptech.neosspringjava.api.controllers.paymentController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentResponse;
import school.sptech.neosspringjava.api.mappers.paymentMapper.PaymentMapper;
import school.sptech.neosspringjava.service.paymentService.PaymentService;

@RestController
@RequestMapping("/payments")    
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll() {
        return ResponseEntity.ok(PaymentMapper.toResponse(paymentService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(PaymentMapper.toResponse(paymentService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> update(@PathVariable Integer id, @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.update(id, paymentRequest);
        if (paymentResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-rent/by-establishment/{id}")
    public ResponseEntity<Double> getTotalRentByEstablishment(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getTotalRentByEstablishment(id));
    }

    // @GetMapping("/total-rent/by-establishment/{id}?start-date=?{startDate}")
    // public ResponseEntity<Double> getTotalRentByEstablishmentAndStartDate(@PathVariable Integer id, @PathVariable String startDate) {
    //     return ResponseEntity.ok(paymentService.getTotalRentByEstablishmentAndStartDate(id, startDate));
    // }

    // @GetMapping("/total-rent/by-establishment/{id}?start-date=?&end-date=?")
    // public ResponseEntity<Double> getTotalRentByEstablishmentAndEndDate(@PathVariable Integer id,  @PathVariable String endDate) {
    //     return ResponseEntity.ok(paymentService.getTotalRentByEstablishmentAndEndDate(id, endDate));
    // }
 

    @GetMapping("/total-rent/by-establishment/{id}/by-employee/{idE}")
    public ResponseEntity<Double> getTotalRentByEstablishmentAndEmployee(@PathVariable Integer id, @PathVariable Integer idE) {
        return ResponseEntity.ok(paymentService.getTotalRentByEstablishmentAndEmployee(id, idE));
    }
    // /total-rent/by-establishment/{id}/by-employee/{id}?start-date=?{startDate}
    @GetMapping("/total-rent/by-establishment/{id}/by-employee/{idE}?/start-date=?{startDate}")
    public ResponseEntity<Double> getTotalRentByEstablishmentAndEmployeeAndStartDate(@PathVariable Integer id, @PathVariable Integer idE, @PathVariable String startDate) {
        return ResponseEntity.ok(paymentService.getTotalRentByEstablishmentAndEmployeeAndStartDate(id, idE, startDate));
    }
    // /total-rent/by-establishment/{id}/by-employee/{id}?start-date=?&end-date=?{endDate}
    @GetMapping("/total-rent/by-establishment/{id}/by-employee/{idE}/end-date/{endDate}")
    public ResponseEntity<Double> getTotalRentByEstablishmentAndEmployeeAndEndDate(@PathVariable Integer id, @PathVariable Integer idE, @PathVariable String endDate) {
        return ResponseEntity.ok(paymentService.getTotalRentByEstablishmentAndEmployeeAndEndDate(id, idE, endDate));
    }

}
