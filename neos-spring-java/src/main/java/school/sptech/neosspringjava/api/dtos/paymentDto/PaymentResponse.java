package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Market;
import school.sptech.neosspringjava.domain.model.Product;
import school.sptech.neosspringjava.domain.model.Scheduling;
import school.sptech.neosspringjava.domain.model.Status;

public record PaymentResponse(
    Integer id,
    LocalDateTime datePayment,
    Scheduling schedule,
    Market market,
    Status status
) {}