package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

public record PaymentRequest(
    LocalDateTime datePayment,
    Double total,
    Integer fkSchedule,
    Integer fkOrder,
    Integer fkStatus
) {}
