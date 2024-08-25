package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import lombok.Data;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.status.Status;


@Data
public class EstablishmentResponse {
     private Integer id;
     private String name;
     private String imgUrl;
     private Local local;
     private Status status;
     private String aditumId;
     private Integer totalRatings;
     private Double media;
}