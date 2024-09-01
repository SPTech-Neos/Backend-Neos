package school.sptech.neosspringjava.api.mappers.paymentMapper;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.paymentDto.PaymentRequest;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.payment.Payment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.paymentRepository.PaymentRepository;
import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final EstablishmentRepository establishmentRepository;



    public Payment of(PaymentRequest paymentRequest){

        Product product = productRepository
            .findById(paymentRequest.productId())
            .orElseThrow(() -> new RuntimeException("Product not found"));

        Client client = clientRepository
            .findById(paymentRequest.clientId())
            .orElseThrow(() -> new RuntimeException("Client not found"));

        Establishment establishment = establishmentRepository
            .findById(paymentRequest.establishmentId())
            .orElseThrow(() -> new RuntimeException("Establishment not found"));


        Payment payment = new Payment();
                payment.setEstablishment(establishment);
                payment.setClient(client);
                payment.setProduct(product);
                payment.setDateTime(paymentRequest.dateTime());
                payment.setValue(paymentRequest.value());
        return payment;
    }

}
