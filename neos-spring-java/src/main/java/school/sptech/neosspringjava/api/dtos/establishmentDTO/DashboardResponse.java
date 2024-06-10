package school.sptech.neosspringjava.api.dtos.establishmentDTO;


public record DashboardResponse(
    Double percentageOccupation,
    Double totalIncome,
    Double cancellationRate,
    Double cancellationByEmployee,
    Double totalIncomeByServiceCategory
) {

}
