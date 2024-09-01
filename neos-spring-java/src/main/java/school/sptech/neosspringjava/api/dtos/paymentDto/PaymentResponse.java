package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

import school.sptech.neosspringjava.domain.model.order.Order;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.status.Status;

public record PaymentResponse(
    Integer id,
    LocalDateTime datePayment,
    Double total,
    Scheduling schedule,
    Order order,
    Status status
) {}
