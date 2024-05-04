package school.sptech.neosspringjava.api.controllers.clientController;

import java.util.Optional;

//import javax.swing.text.html.Option;

<<<<<<< HEAD
//import org.apache.catalina.connector.Response;
=======
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
>>>>>>> full-sprint2
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import school.sptech.neosspringjava.api.dtos.clientDto.ClientLoginDto;
=======
import school.sptech.neosspringjava.api.dtos.clientDTO.ClientCreatDTO;
import school.sptech.neosspringjava.api.dtos.clientDTO.ClientLoginDTO;
import school.sptech.neosspringjava.api.dtos.clientDTO.ClientTokenDto;
>>>>>>> full-sprint2
import school.sptech.neosspringjava.api.dtos.clientDto.ClientRequest;
import school.sptech.neosspringjava.api.dtos.clientDto.ClientResponse;
import school.sptech.neosspringjava.api.mappers.clientMapper.ClientMapper;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.client.ClientService;

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

//    @PostMapping("/login")
//    public ResponseEntity<ClientResponse> Login(@RequestBody ClientLoginDTO clientLoginDTO) {
//
//        Client client = clientRepository.findByEmailAndPassword(clientLoginDTO.email(), clientLoginDTO.password());
//        if(client == null){
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok().body(ClientMapper.toClientResponse(client));
//
//    }


    //    @PostMapping
//    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
//        if (clientRepository.existsByEmail(clientRequest.email())) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//
//        Client client = new Client();
//        client.setName(clientRequest.name());
//        client.setEmail(clientRequest.email());
//        client.setPassword(clientRequest.password());
//        client.setLocal(localRepository.findById(clientRequest.local()).orElse(null));
//
//        clientRepository.save(client);
//        return ResponseEntity.ok(ClientMapper.toClientResponse(client));
//    }


    // Login com JWT
    @PostMapping("/login")
<<<<<<< HEAD
    public ResponseEntity<ClientResponse> Login(@RequestBody ClientLoginDto clientLoginDto) {
    
        Client client = clientRepository.findByEmailAndPassword(clientLoginDto.email(), clientLoginDto.password());
        if(client == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(ClientMapper.toClientResponse(client));

=======
    public ResponseEntity<ClientTokenDto> login(@RequestBody ClientLoginDTO clientLoginDTO){
        ClientTokenDto clientToken = this.clientService.authenticate(clientLoginDTO);
        return ResponseEntity.status(200).body(clientToken);
>>>>>>> full-sprint2
    }

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ClientCreatDTO clientCreatDTO){
        this.clientService.creat(clientCreatDTO);
        return ResponseEntity.status(201).build();
    }



    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable int id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ClientMapper.toClientResponse(client.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable int id, @RequestBody ClientRequest clientRequest) {
        Client client = clientRepository.findById(id).orElseThrow();
        if(client == null){
            return ResponseEntity.notFound().build();
        }
        client.setName(clientRequest.name());
        client.setEmail(clientRequest.email());
        client.setPassword(clientRequest.password());
        client.setLocal(localRepository.findById(clientRequest.local()).orElse(null));
        clientRepository.save(client);
        return ResponseEntity.ok().body(ClientMapper.toClientResponse(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponse> deleteClient(@PathVariable int id) {
        Client client = clientRepository.findById(id).orElseThrow();
        if(client == null){
            return ResponseEntity.notFound().build();
        }
        clientRepository.delete(client);
        return ResponseEntity.ok().body(ClientMapper.toClientResponse(client));
    }

}
