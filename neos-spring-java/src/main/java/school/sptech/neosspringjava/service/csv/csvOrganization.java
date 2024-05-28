package school.sptech.neosspringjava.service.csv;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.scheduling.Scheduling;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;
import school.sptech.neosspringjava.domain.repository.schedulingRepository.SchedulingRepository;

public class csvOrganization {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private FilterRepository filterRepository;
    List<String[]> linesList = new ArrayList<>();
    // definindo a data atual
    LocalDateTime now = LocalDateTime.now();
    YearMonth yearMonth = YearMonth.from(now);
    LocalDateTime startDate = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
    int lastDayOfMonth = yearMonth.lengthOfMonth();
    LocalDateTime endDate = now.withDayOfMonth(lastDayOfMonth).withHour(23).withMinute(59)
            .withSecond(59).withNano(999999999);

    public String generateSchedulingNote(List<Scheduling> schedulingList) {
        linesList.clear();
        List<Filter> filt = filterRepository
                .findAllByEstablishment(schedulingList.get(0).getEmployee().getEstablishment());
        for (Scheduling scheduling : schedulingList) {
            String price = " ";
            for (Filter filter : filt) {
                if (scheduling.getEmployee().getEstablishment() == filter.getEstablishment()
                        && scheduling.getService() == filter.getService()) {
                    price = "R$" + Double.toString(filter.getPrice());

                }
            }
            String lineVetor[] = new String[4];
            lineVetor[0] = scheduling.getClient().getName();
            lineVetor[1] = scheduling.getService().getServiceType().getServiceCategory().getName() + " "
                    + scheduling.getService().getServiceType().getName() + " "
                    + scheduling.getService().getSpecification();
            lineVetor[2] = scheduling.getEmployee().getName();
            lineVetor[3] = price;
            if (lineVetor[0] != null && !lineVetor[0].isEmpty() && lineVetor[1] != null && !lineVetor[1].isEmpty()
                    && lineVetor[2] != null && !lineVetor[2].isEmpty() && lineVetor[3] != null
                    && !lineVetor[3].isEmpty() && lineVetor[4] != null && !lineVetor[4].isEmpty()) {
                // throw
                return "Não foi possivel gerar nota devido a todos os campos estarem vazios";
            }
            linesList.add(lineVetor);

        }
        csvGenerator csvG = new csvGenerator();
        return csvG.gerarCsv(linesList, "nota");

    }

    public String generateSchedulingReport(Establishment establishment) {

        linesList.clear();

        return schedulingForEmployee(establishment, null);

        // csvGenerator csvG = new csvGenerator();
        // csvG.gerarCsv(linesList, "relatorio");

    };

    public String schedulingForEmployee(Establishment establishment, Integer contador) {
        List<Employee> emploList = new ArrayList<>();

        emploList.addAll(employeeRepository.findAll());

        if (contador == null) {
            contador = emploList.size();
        }

        Employee employee = emploList.get(contador);

        List<Scheduling> schedulingsList = schedulingRepository.findByEmployeeAndDateRange(employee, startDate,
                endDate);

        return makeLine(schedulingsList, contador, establishment);
    }

    public String makeLine(List<Scheduling> schedulingsList, Integer cont, Establishment establishment) {
        String retorno = "xxx";
        List<Filter> filt = filterRepository
                .findAllByEstablishment(schedulingsList.get(0).getEmployee().getEstablishment());

        for (Scheduling scheduling : schedulingsList) {
            String price = " ";
            for (Filter filter : filt) {
                if (scheduling.getEmployee().getEstablishment() == filter.getEstablishment()
                        && scheduling.getService() == filter.getService()) {
                    price = Double.toString(filter.getPrice());

                }
            }
            String lineVetor[] = new String[5];
            lineVetor[0] = scheduling.getClient().getName();
            lineVetor[1] = scheduling.getService().getServiceType().getServiceCategory().getName() + " "
                    + scheduling.getService().getServiceType().getName() + " "
                    + scheduling.getService().getSpecification();
            lineVetor[2] = scheduling.getEmployee().getName();
            lineVetor[3] = price;
            lineVetor[4] = String.valueOf(scheduling.getDateTime());
            if (lineVetor[0] != null && !lineVetor[0].isEmpty() && lineVetor[1] != null && !lineVetor[1].isEmpty()
                    && lineVetor[2] != null && !lineVetor[2].isEmpty() && lineVetor[3] != null
                    && !lineVetor[3].isEmpty() && lineVetor[4] != null && !lineVetor[4].isEmpty()) {
                // throw
                return "Erro ao fazer a linha devido a todos os campos estarem vazios";
            }
            linesList.add(lineVetor);
        }
        if (cont > 0) {
            cont--;
            schedulingForEmployee(establishment, cont);
        } else if (cont == 0) {
            retorno = orderLine(linesList);
        }
        return retorno;
    }

    public String orderLine(List<String[]> lineLst) {
        for (int i = 0; i < lineLst.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < lineLst.size(); j++) {
                LocalDateTime dateTimeMin = LocalDateTime.parse(lineLst.get(minIndex)[4]);
                LocalDateTime dateTimeCurrent = LocalDateTime.parse(lineLst.get(j)[4]);
                if (dateTimeCurrent.isBefore(dateTimeMin)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                String[] temp = lineLst.get(i);
                lineLst.set(i, lineLst.get(minIndex));
                lineLst.set(minIndex, temp);
            }
        }
        confere();
        return csvGenerator.gerarCsv(lineLst, "Relatorio " + now);
    }

    public void confere() {
        for (String[] strings : linesList) {
            System.out.println("BATATA");
            System.out.println(strings[0]);
        }
    }

}
