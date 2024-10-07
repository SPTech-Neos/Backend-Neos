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

    @Column(name = "aditum_id")
    private String aditumId;

    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @JoinColumn(name = "fk_local")
    @ManyToOne
    private Local local;

    @JoinColumn(name = "fk_phone")
    @OneToOne
    private Phone phone;

    @JoinColumn(name = "fk_status")
    @ManyToOne
    private Status status;
    
    @Column(name = "start_shift")
    private LocalTime startShift;

    @Column(name = "end_shift")
    private LocalTime endShift;

    @Column(name = "description")
    private String description;
  
    @Column(name = "cnpj")
    private String cnpj;
}
