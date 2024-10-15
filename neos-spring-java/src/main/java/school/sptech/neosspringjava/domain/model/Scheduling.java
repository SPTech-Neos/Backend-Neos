package school.sptech.neosspringjava.domain.model;


import java.time.LocalDateTime;

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
@Table(name = "schedule")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer id;

    @Column(name = "date_time")
    private LocalDateTime dateTimeSchedule;
    
    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_client")
    private Client client;

    @ManyToOne  
    @JoinColumn(name = "fk_service")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "fk_employee")
    private Employee employee;
}
