
package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String imgUrl;
   
    @ManyToOne
    @JoinColumn(name = "fk_establishment")
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name = "fk_local")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "fk_phone")
    private Phone phone;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_employee_type")
    private EmployeeType employeeType;

}
