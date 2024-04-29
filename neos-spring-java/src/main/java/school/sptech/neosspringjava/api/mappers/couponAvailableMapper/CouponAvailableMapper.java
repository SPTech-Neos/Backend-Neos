package school.sptech.neosspringjava.api.mappers.couponAvailableMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.couponAvailableDto.CouponAvailableRequest;
import school.sptech.neosspringjava.api.dtos.couponAvailableDto.CouponAvailableResponse;
import school.sptech.neosspringjava.domain.model.couponAvailable.CouponAvailable;

@Component
public class CouponAvailableMapper {


    public static CouponAvailableResponse toCouponAvailableResponse(CouponAvailable couponAvailable) {
        return new CouponAvailableResponse(couponAvailable.getId(), couponAvailable.getExpirationDate(), couponAvailable.getDiscount(), couponAvailable.getDiscountType(), couponAvailable.getEstablishment(), couponAvailable.getCoupon());
    }

    public static List<CouponAvailableResponse> toCouponAvailableResponse(List<CouponAvailable> couponAvailables) {
        return couponAvailables.stream().map(CouponAvailableMapper::toCouponAvailableResponse).collect(Collectors.toList());
    }
}
