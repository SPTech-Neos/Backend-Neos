package school.sptech.neosspringjava.domain.model.rating;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.service.Service;

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

    @JoinColumn(name = "fkEstablishment")
    @ManyToOne
    private Establishment establishment;

    @JoinColumn(name = "fkClient")
    @ManyToOne
    private Client client;

    @JoinColumn(name = "fkEmployee")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "fkService")
    @ManyToOne
    private Service service;

    @JoinColumn(name = "fkProduct")
    @ManyToOne
    private Product product;

}
