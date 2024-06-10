package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

public record PaymentRequest(   LocalDateTime dateTime,
    Double value,
    Integer productId,
    Integer clientId,
    Integer establishmentId) {

}
