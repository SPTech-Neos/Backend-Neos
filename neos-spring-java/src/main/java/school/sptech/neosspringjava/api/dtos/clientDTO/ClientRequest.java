package school.sptech.neosspringjava.api.dtos.clientDTO;



import lombok.Builder;

@Builder
public record ClientRequest(String name, String email, String password, Integer local) {

}
