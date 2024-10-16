package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer id;
    private String specification;
    private String aditumId; 
    private Double  price;
    private  String imgUrl;
    @ManyToOne
    @JoinColumn(name = "fk_service_type")
    private ServiceType serviceType;
    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;
}
