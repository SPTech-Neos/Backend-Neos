package school.sptech.neosspringjava.domain.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.status.Status;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    private LocalDateTime dateTime;

    @JoinColumn(name ="fkStatus")
    @ManyToOne
    private Status status;

    @JoinColumn(name ="fkClient")
    @ManyToOne
    private Client client;

    @JoinColumn(name ="fkEmployee")
    @ManyToOne
    private Employee employee;
}
