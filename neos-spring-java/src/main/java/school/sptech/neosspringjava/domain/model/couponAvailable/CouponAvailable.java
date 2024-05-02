package school.sptech.neosspringjava.domain.model.couponAvailable;

import java.util.Date;   
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.coupon.Coupon;
import school.sptech.neosspringjava.domain.model.discountType.DiscountType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponAvailable {
    @Id
    private Integer id;
    @NotNull
    @NotBlank
    @NotEmpty
    private Date expirationDate;
    @NotNull
    @NotBlank
    @NotEmpty
    private Double discount;
    @NotNull
    @NotBlank
    @NotEmpty
    private DiscountType discountType;
    @NotNull
    @NotBlank
    @NotEmpty
    private Establishment establishment;
    @NotNull
    @NotBlank
    @NotEmpty
    private Coupon coupon;
}
