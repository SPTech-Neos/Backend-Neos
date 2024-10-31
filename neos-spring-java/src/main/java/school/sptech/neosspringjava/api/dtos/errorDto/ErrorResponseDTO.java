package school.sptech.neosspringjava.api.dtos.errorDto;


import lombok.Builder;

@Builder
public record ErrorResponseDTO(int status, String message, String timestamp, String path) {

}