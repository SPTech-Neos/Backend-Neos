package school.sptech.neosspringjava.api.dtos.couponAvailableDto;

import java.util.Date;

public record CouponAvailableResponse(Integer id, Date expirationDate, Double discount, Integer fkDiscount, Integer fkEstablishment, Integer fkCoupon) {

}
