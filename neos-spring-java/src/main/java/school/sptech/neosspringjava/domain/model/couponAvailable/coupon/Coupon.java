package school.sptech.neosspringjava.domain.model.couponAvailable.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Coupon {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @NotBlank(message = "Nome do cupom é obrigatório")
    @NotEmpty(message = "Nome do cupom é obrigatório")
    private String name;
    @Override
    public String toString() {
        return "Coupon [id=" + id + ", name=" + name + "]";
    }
    
}
