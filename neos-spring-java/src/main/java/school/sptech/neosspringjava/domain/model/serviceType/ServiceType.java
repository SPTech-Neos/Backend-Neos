package school.sptech.neosspringjava.domain.model.serviceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_type_id")
    private Integer id;
    @NotBlank(message = "Nome do tipo de serviço é obrigatório")
    @NotEmpty(message = "Nome do tipo de serviço é obrigatório")
    @Column(name = "service_type_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private ServiceCategory ServiceCategory;


}
