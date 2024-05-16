package school.sptech.neosspringjava.api.mappers.discountTypeMapper;
import school.sptech.neosspringjava.api.dtos.discountTypeDto.*;
import school.sptech.neosspringjava.domain.model.discountType.*;

import org.springframework.stereotype.Component;

@Component
public class DiscountTypeMapper {

    public DiscountTypeResponse toDiscountTypeResponse(DiscountType discountType) {
        return new DiscountTypeResponse(discountType.getId(), discountType.getName());
    }

    public DiscountType toDiscountType(DiscountTypeRequest discountTypeRequest) {
        return DiscountType.builder().name(discountTypeRequest.name()).build();
    }

    public DiscountType toDiscountType(DiscountTypeRequest discountTypeRequest, Integer id) {
        return DiscountType.builder().id(id).name(discountTypeRequest.name()).build();
    }

}
