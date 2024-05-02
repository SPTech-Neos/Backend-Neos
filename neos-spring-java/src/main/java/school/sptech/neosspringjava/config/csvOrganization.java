// package school.sptech.neosspringjava.config;


// import java.util.List;

// import school.sptech.neosspringjava.domain.model.client.Client;
// import school.sptech.neosspringjava.domain.model.employee.Employee;
// import school.sptech.neosspringjava.domain.model.product.Product;
// import school.sptech.neosspringjava.domain.model.productType.ProductType;
// import school.sptech.neosspringjava.domain.model.service.Service;
// import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
// import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;
// import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
// import school.sptech.neosspringjava.domain.repository.productRepository.ProductRepository;
// import school.sptech.neosspringjava.domain.repository.productTypeRopository.ProductTypeRopository;

// public class csvOrganization {
//     // AgendamentoRepository = agendamentorepository;
//     // ServiçoRepository = serviçoRepository;
//      ProductRepository productRepository;
//      EmployeeRepository employeeRepository;
//      ProductTypeRopository productTypeRopository;


// public void generateSchedulingNote(Scheduling scheduling){

//     String lineVetor[] = new String[8];
//     List<String[]>linesList;


//     lineVetor[0]= scheduling.Client.name;
//     lineVetor[1]= scheduling.Service.ServiceType.ServiceCategory + + scheduling.Service.ServiceType + + scheduling.Service;
//     lineVetor[2]= scheduling.Service.EmployeeService.Employee.name;
//     lineVetor[3]= scheduling.Service.Filter.Price;
    
//     linesList.add(lineVetor);


// }

// public void generateSchedulingReport(){

//     SchedulingRepository = schedulingRepository;

//     List<Scheduling> schedulingList = schedulingRepository.findByIdAndDataBetween(LocalDate start, LocalDate end);
//     String lineVetor[] = new String[8];
//     List<String[]>linesList;

//     for (Scheduling scheduling : schedulingList) {
//         lineVetor[0]= scheduling.Client.name;
//         lineVetor[1]= scheduling.Service.ServiceType.ServiceCategory + + scheduling.Service.ServiceType + + scheduling.Service;
//         lineVetor[2]= scheduling.Service.EmployeeService.Employee.name;
//         lineVetor[3]= scheduling.Service.Filter.Price;
//         linesList.add(lineVetor);
//     }


    

    


// }
// }
