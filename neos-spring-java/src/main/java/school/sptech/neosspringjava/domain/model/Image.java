package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.*;
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
public class Image {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int id;

    private String name;

    private String path;

    private String fileExtension;

    private float fileSize;

    @JoinColumn(name = "fk_client")
    @ManyToOne
    private Client client;

    @JoinColumn(name = "fk_product")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "fk_service")
    @ManyToOne
    private Service service;

    @JoinColumn(name = "fk_employee")
    @ManyToOne
    private Employee employee;

    @JoinColumn(name = "fk_establishment")
    @ManyToOne
    private Establishment establishment;

}

