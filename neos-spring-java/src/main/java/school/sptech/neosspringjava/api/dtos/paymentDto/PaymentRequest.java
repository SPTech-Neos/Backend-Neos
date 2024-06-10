package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

public record PaymentRequest(   LocalDateTime dateTime,
    Integer productId,
    Integer clientId,
    Integer establishmentId) {

}
