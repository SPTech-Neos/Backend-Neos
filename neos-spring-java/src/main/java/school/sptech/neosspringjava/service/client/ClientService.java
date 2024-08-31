package school.sptech.neosspringjava.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.neosspringjava.api.configuration.security.jwt.GerenciadorTokenJwt;

import school.sptech.neosspringjava.api.dtos.clientDto.*;
import school.sptech.neosspringjava.api.mappers.clientMapper.ClientMapper;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ClientMapper clientMapper;
    
    public ClientResponse create(ClientCreatDTO clientCreatDTO) {
        Client newClient = clientMapper.of(clientCreatDTO);

        String passwordEncrypted = passwordEncoder.encode(newClient.getPassword());
        newClient.setPassword(passwordEncrypted);

        Client savedClient =  clientRepository.save(newClient);
        return clientMapper.toClientResponse(savedClient);
    }

    @Autowired
    GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    AuthenticationManager authenticationManager;

    public ClientTokenDto authenticate(ClientLoginDto clientLoginDTO) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(clientLoginDTO.getEmail()+";client", clientLoginDTO.getPassword());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Client clientAuthetication =
                clientRepository.findByEmail(clientLoginDTO.getEmail())
        .orElseThrow(
                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return ClientMapper.of(clientAuthetication, token);
    }

    public Client findById(Integer id){

        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return client;
    }

}
