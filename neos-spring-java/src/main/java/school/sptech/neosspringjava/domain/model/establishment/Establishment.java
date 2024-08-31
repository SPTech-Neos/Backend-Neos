package school.sptech.neosspringjava.domain.model.establishment;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "establishment_id")
    private int id;

    private String name;

    @JoinColumn(name = "fkStatus")
    @ManyToOne
    private Status status;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "aditumId")
    private String aditumId;

    @JoinColumn(name = "fkLocal")
    @ManyToOne
    private Local local;

    @JoinColumn(name = "fkPhone")
    @OneToOne
    private Phone phone;
  
}
