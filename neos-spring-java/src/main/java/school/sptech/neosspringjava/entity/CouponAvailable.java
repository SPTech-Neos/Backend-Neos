package school.sptech.neosspringjava.entity;

import java.sql.Date;   
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class CouponAvailable {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Date expirationDate;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Double discount;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer fkDiscount;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer fkEstablishment;
    @Getter
    @Setter
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer fkCoupon;
}
