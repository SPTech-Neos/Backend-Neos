package school.sptech.neosspringjava.api.dtos.serviceDto;

public record ServiceRequest(
    String specification,
    String aditumId,
    Double price,
    String imgUrl,
    Integer serviceType
) {
} 
