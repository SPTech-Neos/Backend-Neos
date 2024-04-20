package school.sptech.neosspringjava.api.controllers.mappers;


import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.clientDTO.ClientResponse;
import school.sptech.neosspringjava.domain.model.client.Client;

@Component
public class ClientMapper {

    public static ClientResponse toClientResponse(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getEmail(), client.getPassword(), client.getLocal());
    }

    // public static Client toClient(ClientResponse clientResponse) {
    //     return new Client( clientResponse.name(), clientResponse.email(), clientResponse.password(), clientResponse.local());
    // }


}
