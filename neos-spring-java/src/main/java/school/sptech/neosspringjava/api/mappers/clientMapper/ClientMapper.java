package school.sptech.neosspringjava.api.mappers.clientMapper;


import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.clientDto.ClientCreatDTO;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientTokenDto;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientResponse;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.domain.repository.phoneRepository.PhoneRepository;
import school.sptech.neosspringjava.service.localService.LocalService;

@Component
public class ClientMapper {

    @Autowired
    private LocalRepository localRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    public static ClientResponse toClientResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPassword(),
                client.getLocal()
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
        client.setPhone(phoneRepository.findById(clientCreatDTO.getPhone()).orElse(null));
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
