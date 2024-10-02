
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
    @JoinColumn(name = "fkEstablishment")
    private Establishment establishment;

    @ManyToOne
    @JoinColumn(name = "fkLocal")
    private Local local;

    @ManyToOne
    @JoinColumn(name = "fkPhone")
    private Phone phone;

    @ManyToOne
    @JoinColumn(name = "fkStatus")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fkEmployeeType")
    private EmployeeType employeeType;

}
