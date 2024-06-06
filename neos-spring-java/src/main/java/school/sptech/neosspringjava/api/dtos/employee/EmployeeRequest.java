package school.sptech.neosspringjava.api.dtos.employee;
import lombok.Builder;

public record EmployeeRequest(String name,
String email,
String password,
String imgUrl,
Integer fkEstablishment,
Integer employeeType) {
    
}
