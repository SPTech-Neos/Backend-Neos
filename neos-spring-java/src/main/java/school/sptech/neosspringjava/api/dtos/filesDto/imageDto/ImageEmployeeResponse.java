package school.sptech.neosspringjava.api.dtos.filesDto.imageDto;

import school.sptech.neosspringjava.domain.model.employee.Employee;

public record ImageEmployeeResponse(Integer id, String name, String path, String fileExtension, Float fileSize,Integer employeeFk) {

}
