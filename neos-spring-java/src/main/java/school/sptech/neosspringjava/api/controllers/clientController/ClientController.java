package school.sptech.neosspringjava.api.controllers.clientController;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.apache.catalina.connector.Response;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientLoginDTO;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientRequest;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientResponse;
import school.sptech.neosspringjava.api.mappers.clientMapper.ClientMapper;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientRepository clientRepository;
    private final LocalRepository localRepository;
    
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClient(){
        List<Client> clients = clientRepository.findAll();
        if(clients.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok().body(ClientMapper.toClientResponse(clients));
    } 

    @PostMapping("/login")
    public ResponseEntity<ClientResponse> Login(@RequestBody ClientLoginDTO clientLoginDTO) {
    
        Client client = clientRepository.findByEmailAndPassword(clientLoginDTO.email(), clientLoginDTO.password());
        if(client == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(ClientMapper.toClientResponse(client));

    }
    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
        if (clientRepository.existsByEmail(clientRequest.email())) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    
        Client client = new Client();
        client.setName(clientRequest.name());
        client.setEmail(clientRequest.email());
        client.setPassword(clientRequest.password());
        client.setLocal(localRepository.findById(clientRequest.local()).orElse(null));
    
        clientRepository.save(client);
        return ResponseEntity.ok(ClientMapper.toClientResponse(client));
    }

}
