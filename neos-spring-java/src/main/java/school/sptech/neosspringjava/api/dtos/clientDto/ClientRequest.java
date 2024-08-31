package school.sptech.neosspringjava.api.dtos.clientDto;

import lombok.Builder;

@Builder
public record ClientRequest(String name, String email, String password, String imgUrl, Integer fkLocal, Integer fkPhone) {

}
