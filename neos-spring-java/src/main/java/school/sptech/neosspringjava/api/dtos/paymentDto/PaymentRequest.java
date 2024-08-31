package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

public record PaymentRequest(
    LocalDateTime datePayment,
    Integer fkSchedule,
    Integer fkMarket,
    Integer fkStatus){

}
