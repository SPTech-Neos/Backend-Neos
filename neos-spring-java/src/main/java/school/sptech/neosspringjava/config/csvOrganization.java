package school.sptech.neosspringjava.config;


import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;


public class csvOrganization {
    List<String[]> linesList = new ArrayList<>();

public void generateSchedulingNote(Scheduling scheduling){
    linesList.clear();
    String lineVetor[] = new String[4];
    lineVetor[0]= scheduling.getClient().getName();
    lineVetor[1]= scheduling.getService().getServiceType().getFkServiceCategory().getName() +" "+ scheduling.getService().getServiceType().getName() +" "+ scheduling.getService().getSpecification();
    lineVetor[2]= scheduling.getEmployee().getName();
    lineVetor[3]=String.valueOf( scheduling.getService().getFilter().getPrice());
    linesList.add(lineVetor);
csvGenerator csvG = new csvGenerator();
csvG.gerarCsv(linesList,"nota");
}

@Autowired
private EmployeeRepository employeeRepository;
@Autowired
private SchedulingRepository schedulingRepository;

public void generateSchedulingReport(Establishment establishment){

    List<Employee> emploList = employeeRepository.findByEstablishment(establishment);
    linesList.clear();
        LocalDateTime now = LocalDateTime.now();
        YearMonth yearMonth = YearMonth.from(now);
        LocalDateTime firstDayOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        int lastDayOfMonth = yearMonth.lengthOfMonth();
        LocalDateTime lastDayOfMonthDateTime = now.withDayOfMonth(lastDayOfMonth).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    
    for (Employee employee : emploList) {
        List<Scheduling> schedulingsList = schedulingRepository.findByEmployeeAndDateTimeBetween(employee,firstDayOfMonth, lastDayOfMonthDateTime );
    for (Scheduling scheduling : schedulingsList) {
    String lineVetor[] = new String[4];
    lineVetor[0]= scheduling.getClient().getName();
    lineVetor[1]= scheduling.getService().getServiceType().getFkServiceCategory().getName() +" "+ scheduling.getService().getServiceType().getName() +" "+ scheduling.getService().getSpecification();
    lineVetor[2]= scheduling.getEmployee().getName();
    lineVetor[3]=String.valueOf( scheduling.getService().getFilter().getPrice());
    linesList.add(lineVetor);
}
    }

    csvGenerator csvG = new csvGenerator();
    csvG.gerarCsv(linesList,"relatorio");

};

}
