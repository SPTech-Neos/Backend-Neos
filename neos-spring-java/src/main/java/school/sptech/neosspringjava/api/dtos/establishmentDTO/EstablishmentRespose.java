package school.sptech.neosspringjava.api.dtos.establishmentDTO;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;


public record EstablishmentRespose(
    Integer id,
        String name,
         Company company,
        String imgUrl,
        Local local
     ) {

}