package school.sptech.neosspringjava.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeTokenDto;
import school.sptech.neosspringjava.api.mappers.EmployeeMapper;
import school.sptech.neosspringjava.config.security.jwt.GerenciadorTokenJwt;
import school.sptech.neosspringjava.domain.model.Employee;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Status;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.ServiceRepository;
import school.sptech.neosspringjava.domain.model.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

   private final EmployeeRepository employeeRepository;
   private final EstablishmentService establishmentService;
   private final EmployeeTypeService eTypeService;
   private final PasswordEncoder passwordEncoder;
   private final LocalService lService;
   private final PhoneService pService;
   private final StatusService sService;
   private final ServiceRepository servService;
   private final EmployeeServicesRepository employeeServicesRepository;

    @Autowired
    GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    AuthenticationManager authenticationManager;
 
    public EmployeeTokenDto authenticate(EmployeeLogin employeeLogin) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(employeeLogin.email() + ";employee", employeeLogin.password());
        System.out.println("credentials "+credentials);
        final Authentication authentication = this.authenticationManager.authenticate(credentials);
        System.out.println("authentication == "+authentication);
        System.out.println("authentication == "+authentication.getCredentials());

        Employee employeeAuthentication = employeeRepository.findByEmail(employeeLogin.email())
                .orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return EmployeeMapper.of(employeeAuthentication, token);
    }

    public Employee save(EmployeeRequest employeeRequest) {

        String passwordEncrypted = passwordEncoder.encode(employeeRequest.password());

        Employee employee = EmployeeMapper.toEntity(employeeRequest, passwordEncrypted);

        employee.setEmployeeType(eTypeService.findById(employeeRequest.employeeType()));
        employee.setEstablishment(establishmentService.findById(employeeRequest.fkEstablishment()));
        employee.setStatus(sService.findById(employeeRequest.fkStatus()));
        employee.setLocal(lService.findById(employeeRequest.fkLocal()));
        employee.setPhone(pService.findById(employeeRequest.fkPhone()));

        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeRequest employeeRequest, Integer id) {
       
        Employee employee = employeeRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado"));
            employee.setName(employeeRequest.name());
            employee.setEmail(employeeRequest.email());
            employee.setPassword(employeeRequest.password());
            employee.setImgUrl(employeeRequest.imgUrl());
            employee.setEstablishment(establishmentService.findById(employeeRequest.fkEstablishment()));
            employee.setEmployeeType(eTypeService.findById(employeeRequest.employeeType()));
        return employeeRepository.save(employee);
    }

    public Employee partialUpdate(EmployeeRequest updates, Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
    
        if (updates.name() != null) {
            employee.setName(updates.name());
        }
    
        if (updates.email() != null) {
            employee.setEmail(updates.email());
        }
    
        if (updates.password() != null) {
            employee.setPassword(updates.password());
        }
    
        if (updates.imgUrl() != null) {
            employee.setImgUrl(updates.imgUrl());
        }
    
        if (updates.fkEstablishment() != null) {
            Integer fkEstablishment = updates.fkEstablishment();
            employee.setEstablishment(establishmentService.findById(fkEstablishment));
        }
    
        if (updates.fkLocal() != null) {
            Integer fkLocal = updates.fkLocal();
            employee.setLocal(lService.findById(fkLocal));
        }
    
        if (updates.fkPhone() != null) {
            Integer fkPhone = updates.fkPhone();
            employee.setPhone(pService.findById(fkPhone)); 
        }
    
        if (updates.fkStatus() != null) {
            Integer fkStatus = updates.fkStatus();
            employee.setStatus(sService.findById(fkStatus)); 
        }
    
        if (updates.employeeType() != null) {
            Integer employeeType = updates.employeeType();
            employee.setEmployeeType(eTypeService.findById(employeeType));
        }
    
        return employeeRepository.save(employee);
    }
    
    public void delete(Integer id) {
         employeeRepository.deleteById(id);
    }

    public Employee findById(Integer id) {
         return employeeRepository.findById(id).orElseThrow( () -> new RuntimeException("Funcionário não encontrado"));
    }

    public Employee findByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }


    public List<Employee> findAll() {
         return employeeRepository.findAll();
    }

    public List<Employee> findEmployeesByEstablishmentId(Integer id){
        return employeeRepository.findAllByEstablishment(establishmentService.findById(id));
    }

    public Employee deactivate(Integer id){
        Employee e = findById(id);
        Status s = sService.findStatusByName("Inativo");

        if(e.getStatus().equals(s)){
            throw new RuntimeException("Usuário já está inativo");
        }

        e.setStatus(s);

        return employeeRepository.save(e);
    }

    public Employee reactivate(Integer id){
        Employee e = findById(id);
        Status s = sService.findStatusByName("Ativo");

        if(e.getStatus().equals(s)){
            throw new RuntimeException("Usuário já está ativo");
        }

        e.setStatus(s);

        return employeeRepository.save(e);
    }

    public List<Employee> findAllActives(){
        return employeeRepository.findAllByStatus(sService.findStatusByName("Ativo"));
    }

    public List<Employee> findAllInactives(){
        return employeeRepository.findAllByStatus(sService.findStatusByName("Inativo"));
    }


    public List<Employee> findAllByEstablishmentAndService(Integer idEstab, Integer idServ) {
           Establishment estab = establishmentService.findById(idEstab);
           school.sptech.neosspringjava.domain.model.Service serv = servService.findById(idServ).orElseThrow(null);
           return employeeRepository.findAllByEstablishmentAndService(estab,  serv);
    }

}
