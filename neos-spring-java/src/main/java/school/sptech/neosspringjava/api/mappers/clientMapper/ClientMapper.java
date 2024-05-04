package school.sptech.neosspringjava.api.mappers.clientMapper;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.clientDto.ClientCreatDTO;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientTokenDto;
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

    public static Client of(ClientCreatDTO clientCreatDTO){
        Client client = new Client();

        client.setEmail(clientCreatDTO.getEmail());
        client.setName(clientCreatDTO.getName());
        client.setPassword(clientCreatDTO.getPassword());

        return client;
    }

    public static ClientTokenDto of(Client client, String token){
        ClientTokenDto clientTokenDto = new ClientTokenDto();

        clientTokenDto.setClientId(client.getId());
        clientTokenDto.setEmail(client.getEmail());
        clientTokenDto.setName(client.getName());
        clientTokenDto.setToken(token);

        return clientTokenDto;
    }

    // public static Client toClient(ClientResponse clientResponse) {
    //     return new Client( clientResponse.name(), clientResponse.email(), clientResponse.password(), clientResponse.local());
    // }


}
