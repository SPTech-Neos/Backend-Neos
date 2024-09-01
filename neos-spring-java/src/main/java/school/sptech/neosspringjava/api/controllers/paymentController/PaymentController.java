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
import school.sptech.neosspringjava.service.paymentService.PaymentService;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable Integer id) {
        PaymentResponse paymentResponse = paymentService.findById(id);
        if (paymentResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentResponse);
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

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<PaymentResponse>> getSchedulesByClientId(@PathVariable Integer clientId) {
        List<PaymentResponse> payments = paymentService.getPaymentsByClientId(clientId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/establishment/{establishmentId}")
    public ResponseEntity<List<PaymentResponse>> getSchedulesByEstablishmentId(@PathVariable Integer establishmentId) {
        List<PaymentResponse> payments = paymentService.getPaymentsByEstablishmentId(establishmentId);
        return ResponseEntity.ok(payments);
    }

}
