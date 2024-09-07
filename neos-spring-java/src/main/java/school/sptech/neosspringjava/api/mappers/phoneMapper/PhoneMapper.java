package school.sptech.neosspringjava.api.mappers.phoneMapper;

import school.sptech.neosspringjava.api.dtos.phoneDto.PhoneRequest;
import school.sptech.neosspringjava.api.dtos.phoneDto.PhoneResponse;

import school.sptech.neosspringjava.domain.model.phone.Phone;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneMapper {

    public static Phone toEntity(PhoneRequest pDto){

        Phone p = new Phone();

        p.setAreaCode(pDto.areaCode());
        p.setCountryCode(pDto.countryCode());
        p.setNumber(pDto.number());

        return p;
    }

    public static PhoneResponse toResponse (Phone p){
        return new PhoneResponse(
                p.getId(),
                p.getCountryCode(),
                p.getAreaCode(),
                p.getNumber()
        );
    }

    public static List<PhoneResponse> toResponses(List<Phone> p) {
        return p.stream().map(PhoneMapper::toResponse).collect(Collectors.toList());
    }
}
