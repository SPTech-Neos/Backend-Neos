package school.sptech.neosspringjava.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class EmployeeServices {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    private Date hoursSpent;
    @Getter
    @Setter
    private Boolean expertise;
    @Getter
    @Setter
    private Integer fkEmployee;
    @Getter
    @Setter
    private Integer fkService;

}

