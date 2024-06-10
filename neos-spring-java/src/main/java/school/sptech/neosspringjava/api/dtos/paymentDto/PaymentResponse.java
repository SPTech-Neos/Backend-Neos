package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;

public record PaymentResponse(
    Integer id,
    LocalDateTime dateTime,
    Product product,
    Client client,
    Establishment establishment
) {}