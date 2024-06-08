package school.sptech.neosspringjava.service.user.authentication;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.usersDto.UsersDetailsDto;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.UserRepository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Object> userOpt = userRepository.findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuário: %s não encontrado", username));
        }

        Object user = userOpt.get();
        if (user instanceof Client) {
            return new UsersDetailsDto((Client) user);
        } else if (user instanceof Employee) {
            return new UsersDetailsDto((Employee) user);
        } else {
            throw new IllegalStateException("Tipo de usuário desconhecido");
        }
    }
}