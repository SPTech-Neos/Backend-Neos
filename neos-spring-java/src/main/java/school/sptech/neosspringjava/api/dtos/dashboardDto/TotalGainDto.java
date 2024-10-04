package school.sptech.neosspringjava.api.dtos.dashboardDto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalGainDto {
    public LocalDateTime dateTimeStart;
    public LocalDateTime dateTimeEnd;
    public Double value;
}
