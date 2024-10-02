package school.sptech.neosspringjava.domain.model;

import java.util.Date;

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
public class EmployeeServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_services_id")
    private Integer id;

    private Date hoursSpent;

    private Boolean expertise;

    private Double price;

    private String aditumId;

    @ManyToOne
    @JoinColumn(name = "fkEmployee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "fkService")
    private Service service;

}

