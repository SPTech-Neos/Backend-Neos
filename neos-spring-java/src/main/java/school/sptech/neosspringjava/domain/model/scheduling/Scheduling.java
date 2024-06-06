package school.sptech.neosspringjava.domain.model.scheduling;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.product.Product;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.schedulingStatus.* ;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduling_id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "status_fk")
    private schedulingStatus schedulingStatus;

    @ManyToOne
    @JoinColumn(name = "client_fk")
    private Client client;

    @ManyToOne  
    @JoinColumn(name = "service_fk")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "employee_fk")
    private Employee employee;

    private LocalDateTime dateTime;
}
