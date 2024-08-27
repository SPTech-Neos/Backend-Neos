package school.sptech.neosspringjava.domain.model.phone;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
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
