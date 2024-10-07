package school.sptech.neosspringjava.api.mappers;


import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.clientDto.ClientCreatDTO;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientTokenDto;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientResponse;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.repository.LocalRepository;
import school.sptech.neosspringjava.service.LocalService;

@Component
public class ClientMapper {

    @Autowired
    private LocalRepository localRepository;

    public static ClientResponse toClientResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPassword(),
                client.getLocal(),
                client.getPhone()
        );
    }

    public static List<ClientResponse> toClientResponse(List<Client> clients) {
        return clients.stream().map(ClientMapper::toClientResponse).collect(Collectors.toList());
    }

    public Client of(ClientCreatDTO clientCreatDTO) {
        Client client = new Client();

        client.setEmail(clientCreatDTO.getEmail());
        client.setName(clientCreatDTO.getName());
        client.setPassword(clientCreatDTO.getPassword());

        Integer localId = clientCreatDTO.getLocal();
        Local local = localRepository.findById(localId).orElse(null);
        client.setLocal(local);

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
