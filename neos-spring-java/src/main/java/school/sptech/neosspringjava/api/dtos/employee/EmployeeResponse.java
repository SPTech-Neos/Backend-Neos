package school.sptech.neosspringjava.api.dtos.employee;

public record EmployeeResponse(String name,
        String email,
        String password,
        Integer fkEstablishment,
        Integer fkEmployeeType) {

}
