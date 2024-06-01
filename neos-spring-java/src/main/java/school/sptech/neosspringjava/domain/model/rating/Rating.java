package school.sptech.neosspringjava.domain.model.rating;


import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
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
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

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
    private Integer nota;
    @JoinColumn(name = "establishment_fk")
    @ManyToOne
    private Establishment establishment;
    @JoinColumn(name = "client_fk")
    @ManyToOne
    private Client client;


}
