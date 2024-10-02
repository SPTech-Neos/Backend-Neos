package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Integer id;

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "areaCode")
    private String areaCode;

    @Column(name = "number")
    private String number;

}
