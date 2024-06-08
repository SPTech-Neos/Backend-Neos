// package school.sptech.neosspringjava.service.client.authentication;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import school.sptech.neosspringjava.api.dtos.clientDto.ClientDetailsDto;
// import school.sptech.neosspringjava.domain.model.client.Client;
// import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;

// import java.util.Optional;

// @Service
// public class AuthenticationService implements UserDetailsService {

//     @Autowired
//     ClientRepository clientRepository;

//     // consultar usuários

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Optional<Client> clientOpt = clientRepository.findByEmail(username);
//         if (clientOpt.isEmpty()){
//             throw new UsernameNotFoundException(String.format("Usuário: s? não encontrado", username));
//         }

//         return new ClientDetailsDto(clientOpt.get());
//     }
// }
