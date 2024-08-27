package school.sptech.neosspringjava.domain.model.service;

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
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer id;

    @NotBlank(message = "É necessario especificação do serviço")
    @NotEmpty(message = "É necessario especificação do serviço")
    private String specification;

    private  String imgUrl;

    private String aditumId;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "fkServiceType")
    private ServiceType serviceType;

  
    

}
