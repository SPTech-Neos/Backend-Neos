package school.sptech.neosspringjava.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.model.service.Service;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;
import school.sptech.neosspringjava.domain.repository.ServiceTypeRepository.ServiceTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.serviceCategoryRepository.ServiceCategoryRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;

@Component
public class mocData  implements CommandLineRunner {
    


    @Autowired
    private EstablishmentRopository establishmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private FilterRepository filterRepository;

    @Override
    public void run(String... args) throws Exception {


        // Insira registros semelhantes para outras entidades

        // Estabelecimentos
        Establishment establishment1 = Establishment.builder()
                .name("Estabelecimento 1")
                .build();
        establishmentRepository.save(establishment1);

        Establishment establishment2 = Establishment.builder()
                .name("Estabelecimento 2")
                .build();
        establishmentRepository.save(establishment2);


        // Tipos de funcionário
        EmployeeType employeeType1 = EmployeeType.builder()
                .name("Tipo 1")
                .build();
        employeeTypeRepository.save(employeeType1);

        EmployeeType employeeType2 = EmployeeType.builder()
                .name("Tipo 2")
                .build();
        employeeTypeRepository.save(employeeType2);


        // Serviços
        Service service1 = Service.builder()
                .specification("Serviço 1")
                .build();
        serviceRepository.save(service1);

        Service service2 = Service.builder()
                .specification("Serviço 2")
                .build();
        serviceRepository.save(service2);


        // Categorias de serviço
        ServiceCategory serviceCategory1 = ServiceCategory.builder()
                .name("Categoria 1")
                .build();
        serviceCategoryRepository.save(serviceCategory1);

        ServiceCategory serviceCategory2 = ServiceCategory.builder()
                .name("Categoria 2")
                .build();
        serviceCategoryRepository.save(serviceCategory2);


        // Tipos de serviço
        ServiceType serviceType1 = ServiceType.builder()
                .name("Tipo de Serviço 1")
                .fkServiceCategory(serviceCategory1)
                .build();
        serviceTypeRepository.save(serviceType1);

        ServiceType serviceType2 = ServiceType.builder()
                .name("Tipo de Serviço 2")
                .fkServiceCategory(serviceCategory2)
                .build();
        serviceTypeRepository.save(serviceType2);

        Filter filter1 = Filter.builder()
        .price(50.0) // Defina o preço conforme necessário
        .establishment(establishment1)
        .service(service1)
        .build();
        filterRepository.save(filter1);

        // Funcionários
        Employee employee1 = Employee.builder()
                .name("Funcionário 1")
                .email("funcionario1@example.com")
                .passaword("senhaFuncionario1")
                .establishment(establishment1)
                .employeeType(employeeType1)
                .build();
        employeeRepository.save(employee1);

        Employee employee2 = Employee.builder()
                .name("Funcionário 2")
                .email("funcionario2@example.com")
                .passaword("senhaFuncionario2")
                .establishment(establishment2)
                .employeeType(employeeType2)
                .build();
        employeeRepository.save(employee2);

            // Busque alguns clientes, serviços e funcionários existentes no banco de dados


        // Adicione alguns agendamentos de exemplo
        Scheduling scheduling1 = Scheduling.builder()
                .client(null)
                .service(service1)
                .employee(employee1)
                .dateTime(LocalDate.of(2024, 5, 10))
                .build();
        schedulingRepository.save(scheduling1);

        Scheduling scheduling2 = Scheduling.builder()
                .client(null)
                .service(service2)
                .employee(employee1)
                .dateTime(LocalDate.of(2024, 5, 12))
                .build();
        schedulingRepository.save(scheduling2);


    }
}
