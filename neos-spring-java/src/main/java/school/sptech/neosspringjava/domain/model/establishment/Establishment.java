package school.sptech.neosspringjava.domain.model.establishment;

import java.time.LocalTime;
import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.local.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Establishment")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome é obrigatório")
    @NotEmpty(message = "Nome é obrigatório")
    private String name;
    
    @CNPJ
    private String cnpj;

    @NotNull
    private LocalTime startShift;
    
    @NotNull
    private LocalTime endShift;
    
    @Max(value = 5, message = "Numero ultrapassou o limite de 5")
    @Min(value = 0, message = "Numero não atingiu o minimo 0")
    private Double  assessment;
    private Integer qtdAssessment;
    
    @ManyToOne
    private Local local;
    
    private String description;
    
    @OneToMany(mappedBy = "establishment")
    private List<Filter> filters;

    private String profilePic;

    @ElementCollection
    private List<Integer> fkServices;
}
