package school.sptech.neosspringjava.domain.model;

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

    @Column(name = "aditumId")
    private String aditumId;

    private String name;

    @Column(name = "imgUrl")
    private String imgUrl;

    @JoinColumn(name = "fkLocal")
    @ManyToOne
    private Local local;

    @JoinColumn(name = "fkPhone")
    @OneToOne
    private Phone phone;

    @JoinColumn(name = "fkStatus")
    @ManyToOne
    private Status status;
    
    @Column(name = "startShift")
    private LocalTime startShift;

    @Column(name = "endShift")
    private LocalTime endShift;

    @Column(name = "description")
    private String description;
  
    @Column(name = "cnpj")
    private String cnpj;
}
