package school.sptech.neosspringjava.service.user.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.usersDto.UsersDetailsDto;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     Optional<Object> userOpt = userRepository.findByEmail(username);
    //     if (userOpt.isEmpty()) {
    //         throw new UsernameNotFoundException(String.format("Usuário: %s não encontrado", username));
    //     }

    //     Object user = userOpt.get();
    //     if (user instanceof Client) {
    //         return new UsersDetailsDto((Client) user);
    //     } else if (user instanceof Employee) {
    //         return new UsersDetailsDto((Employee) user);
    //     } else {
    //         throw new IllegalStateException("Tipo de usuário desconhecido");
    //     }
    // }
    private final EmployeeRepository employeeRepository;
    
    private final ClientRepository clientRepository;

    // consultar usuários

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        if(username.split(";")[1].equals("employee"))
        {Optional<Employee> employeeOpt = employeeRepository.findByEmail(username.split(";")[0]);

        if (employeeOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuário: s? não encontrado", username));
        }

        return new UsersDetailsDto(employeeOpt.get().getName(), employeeOpt.get().getEmail()+";"+username.split(";")[1],employeeOpt.get().getPassword() );
        }else
        
        if(username.split(";")[1].equals("client"))
        {
   
           Optional<Client> clientOpt = clientRepository.findByEmail(username.split(";")[0]);
   
           if (clientOpt.isEmpty()) {
               throw new UsernameNotFoundException(String.format("Usuário: s? não encontrado", username));
           }
   
           return new UsersDetailsDto(clientOpt.get().getName(),clientOpt.get().getEmail()+";"+username.split(";")[1],clientOpt.get().getPassword() );
       } else {
        throw new UsernameNotFoundException("Tipo de usuário desconhecido: " + username.split(";")[1]);
    }
    }




}