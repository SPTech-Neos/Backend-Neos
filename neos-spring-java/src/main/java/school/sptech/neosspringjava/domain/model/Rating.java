package school.sptech.neosspringjava.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
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
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer id;

    @NotNull(message = "A nota é obrigatória")
    private Integer avaliation;

    @JoinColumn(name = "fk_establishment")
    @ManyToOne
    private Establishment establishment;

    @JoinColumn(name = "fk_client")
    @ManyToOne
    private Client client;

    @JoinColumn(name = "fk_employee")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "fk_service")
    @ManyToOne
    private Service service;

    @JoinColumn(name = "fk_product")
    @ManyToOne
    private Product product;

}
