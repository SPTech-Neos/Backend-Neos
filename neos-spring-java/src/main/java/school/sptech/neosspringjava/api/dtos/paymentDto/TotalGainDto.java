package school.sptech.neosspringjava.api.dtos.paymentDto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;

@Getter
@Setter
public class TotalGainDto {
    public LocalDateTime dateTimeStart;
    public LocalDateTime dateTimeEnd;
    public Double value;
}
