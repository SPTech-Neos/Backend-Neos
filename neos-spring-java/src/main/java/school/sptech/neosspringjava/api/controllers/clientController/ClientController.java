package school.sptech.neosspringjava.api.controllers.clientController;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.controllers.mappers.ClientMapper;
import school.sptech.neosspringjava.api.dtos.clientDTO.ClientLoginDTO;
import school.sptech.neosspringjava.api.dtos.clientDTO.ClientRequest;
import school.sptech.neosspringjava.api.dtos.clientDTO.ClientResponse;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.repository.LocalRepository;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {


    private final ClientRepository clientRepository;
    private final LocalRepository localRepository;
    private final ClientMapper clientMapper;
    
    @GetMapping
    public ResponseEntity<ClientResponse> getClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(clientMapper.toClientResponse(client.get()));
    }

    @PostMapping("/login")
    public ResponseEntity<ClientResponse> Login(@RequestBody ClientLoginDTO clientLoginDTO) {
    
        Client client = clientRepository.findByEmailAndPassword(clientLoginDTO.email(), clientLoginDTO.password());
        if(client == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clientMapper.toClientResponse(client));

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
        return ResponseEntity.ok(clientMapper.toClientResponse(client));
    }

}
