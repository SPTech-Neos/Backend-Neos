package school.sptech.neosspringjava.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @JoinColumn(name ="fk_status")
    @ManyToOne
    private Status status;

    @JoinColumn(name ="fk_client")
    @ManyToOne
    private Client client;
}
