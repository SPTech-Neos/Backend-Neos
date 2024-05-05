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
    private Integer fkDiscount;
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer fkEstablishment;
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer fkCoupon;
}
