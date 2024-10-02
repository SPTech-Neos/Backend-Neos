package school.sptech.neosspringjava.service.employee.authentication;
// package school.sptech.neosspringjava.service.employeeService.authentication;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import school.sptech.neosspringjava.api.dtos.clientDto.ClientDetailsDto;
// import school.sptech.neosspringjava.api.dtos.employee.EmployeeDetailsDto;
// import school.sptech.neosspringjava.domain.model.client.Client;
// import school.sptech.neosspringjava.domain.model.employee.Employee;
// import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;
// import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;

// import java.util.Optional;

// @Service
// public class AuthenticationService implements UserDetailsService {

//     // @Bean(name = "employeeAuthenticationService")
//     // public AuthenticationService authenticationService() {
//     //     return new AuthenticationService();
//     // }

//     @Autowired
//     EmployeeRepository employeeRepository;

//     // consultar usuários

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
//         Optional<Employee> employeeOpt = employeeRepository.findByEmail(username.split(";")[0]);

//         if (employeeOpt.isEmpty()){
//             throw new UsernameNotFoundException(String.format("Usuário: s? não encontrado", username));
//         }

//         return new EmployeeDetailsDto(employeeOpt.get());
//     }
// }
