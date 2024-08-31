package school.sptech.neosspringjava.domain.model.scheduling;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.status.Status;

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

    @Column(name = "dateTime")
    private LocalDateTime dateTimeSchedule;
    
    @ManyToOne
    @JoinColumn(name = "fkStatus")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fkClient")
    private Client client;

    @ManyToOne  
    @JoinColumn(name = "fkService")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "fkEmployee")
    private Employee employee;
}
