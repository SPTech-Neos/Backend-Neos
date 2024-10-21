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

    private Integer hoursSpent;

    private Boolean expertise;

    @ManyToOne
    @JoinColumn(name = "fk_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "fk_service")
    private Service service;

}

