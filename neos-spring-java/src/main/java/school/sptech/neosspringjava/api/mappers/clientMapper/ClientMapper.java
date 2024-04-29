package school.sptech.neosspringjava.api.mappers.clientMapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.clientDto.ClientRequest;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientResponse;
import school.sptech.neosspringjava.domain.model.client.Client;

@Component
public class ClientMapper {

    public static ClientResponse toClientResponse(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getEmail(), client.getPassword(), client.getLocal());
    }

    public static List<ClientResponse> toClientResponse(List<Client> clients) {
        return clients.stream().map(ClientMapper::toClientResponse).collect(Collectors.toList());
    }


}
