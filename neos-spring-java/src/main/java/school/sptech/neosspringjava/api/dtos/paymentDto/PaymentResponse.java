package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.market.Market;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.status.Status;

public record PaymentResponse(
    Integer id,
    LocalDateTime datePayment,
    Scheduling schedule,
    Market market,
    Status status
) {}