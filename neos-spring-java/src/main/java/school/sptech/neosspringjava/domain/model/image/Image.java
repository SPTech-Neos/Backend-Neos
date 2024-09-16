package school.sptech.neosspringjava.domain.model.image;

import jakarta.persistence.*;
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
public class Image {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    private String name;

    private String path;

    private String fileExtension;

    private float fileSize;

    @JoinColumn(name = "fkClient")
    @ManyToOne
    private Client client;

    @JoinColumn(name = "fkProduct")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "fkService")
    @ManyToOne
    private Service service;

    @JoinColumn(name = "fkEmployee")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "fkEstablishment")
    @ManyToOne
    private Establishment establishment;

}

