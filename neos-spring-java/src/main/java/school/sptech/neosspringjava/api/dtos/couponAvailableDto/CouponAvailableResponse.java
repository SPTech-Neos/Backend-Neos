package school.sptech.neosspringjava.api.dtos.couponAvailableDto;

import java.util.Date;

import lombok.Builder;
import school.sptech.neosspringjava.domain.model.coupon.Coupon;
import school.sptech.neosspringjava.domain.model.discountType.DiscountType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Builder
public record CouponAvailableResponse(Integer id, Date expirationDate, Double discount, DiscountType discountType, Establishment establishment, Coupon coupon) {


}
