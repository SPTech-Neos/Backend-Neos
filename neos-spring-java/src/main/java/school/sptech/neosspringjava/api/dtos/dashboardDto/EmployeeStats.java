package school.sptech.neosspringjava.api.dtos.dashboardDto;

public record EmployeeStats(
    String imgUrl,
    String name,
    Double mediaAvaliation,
    Integer totalHoursSpent,
    Double totalValue
) {

}
