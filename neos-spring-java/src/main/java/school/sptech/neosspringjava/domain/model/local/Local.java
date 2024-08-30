package school.sptech.neosspringjava.domain.model.local;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_id")
    private Integer id;

    private int number;

    private int floor;
    
    private String block;

    private String complement;
    
    @JoinColumn(name ="fkAddress")
    @ManyToOne
    private Address address;
}
