package school.sptech.neosspringjava.service.employeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRelacionamento;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeTokenDto;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.EmployeeTypeRepository.EmployeeTypeRepository;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.service.EmployeeServService.EmployeeServService;

@Service
@RequiredArgsConstructor
public class EmployeeService {

   private final EmployeeRepository employeeRepository;
   private final EmployeeMapper employeeMapper;
   private final EstablishmentRepository establishmentRepository;
   private final EmployeeTypeRepository employeeTypeRepository;
   private final PasswordEncoder passwordEncoder;
   private final EmployeeServService employeeServService;

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
   


    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        if (employeeRequest.employeeType() == null) {
            throw new IllegalArgumentException("Employee type must not be null");
        }

        EmployeeType employeeType = employeeTypeRepository.findById(employeeRequest.employeeType())
                .orElseThrow(() -> new RuntimeException("Employee type not found"));
        
        String passwordEncrypted = passwordEncoder.encode(employeeRequest.password());

        Employee employee = new Employee();
        employee.setEmail(employeeRequest.email());
        employee.setEmployeeType(employeeType);
        employee.setEstablishment(establishmentRepository.findById(employeeRequest.fkEstablishment())
                .orElseThrow(() -> new RuntimeException("Establishment not found")));
        employee.setImgUrl(employeeRequest.imgUrl());
        employee.setName(employeeRequest.name());
        employee.setPassword(passwordEncrypted);

        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public EmployeeResponse update(EmployeeRequest employeeRequest, Integer id) {
       
        Employee employee = employeeRepository.findById(id).orElseThrow();
            employee.setName(employeeRequest.name());
            employee.setEmail(employeeRequest.email());
            employee.setPassword(employeeRequest.password());
            employee.setImgUrl(employeeRequest.imgUrl());
            employee.setEstablishment(establishmentRepository.findById(employeeRequest.fkEstablishment()).orElseThrow());
            employee.setEmployeeType(employeeTypeRepository.findById(employeeRequest.employeeType()).orElseThrow());
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public EmployeeResponse partialUpdate(Map<String, Object> updates, Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();

        if (updates.containsKey("name")) {
            employee.setName((String) updates.get("name"));
        }

        if (updates.containsKey("email")) {
            employee.setEmail((String) updates.get("email"));
        }

        if (updates.containsKey("password")) {
            employee.setPassword((String) updates.get("password"));
        }

        if (updates.containsKey("imgUrl")) {
            employee.setImgUrl((String) updates.get("imgUrl"));
        }

        if (updates.containsKey("fkEstablishment")) {
            Integer fkEstablishment = (Integer) updates.get("fkEstablishment");
            employee.setEstablishment(establishmentRepository.findById(fkEstablishment).orElseThrow());
        }

        if (updates.containsKey("employeeType")) {
            Integer employeeType = (Integer) updates.get("employeeType");
            employee.setEmployeeType(employeeTypeRepository.findById(employeeType).orElseThrow());
        }

        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public void delete(Integer id) {
         employeeRepository.deleteById(id);
    }

    public EmployeeResponse findById(Integer id) {
         return employeeMapper.toEmployeeResponse(employeeRepository.findById(id).orElseThrow());
    }

    public EmployeeResponse findByEmailAndPassword(String email, String password) {
        return employeeMapper.toEmployeeResponse(employeeRepository.findByEmailAndPassword(email, password));
    }


    public List<EmployeeResponse> findAll() {
         return employeeMapper.toEmployeeResponse(employeeRepository.findAll());
    }
    
    public List<EmployeeRelacionamento> findAllByEstablishment(Integer fkEstablishment) {
    try {
        Optional<Establishment> establishment = establishmentRepository.findById(fkEstablishment);
        if (establishment.isEmpty()) {
            throw new NullPointerException("Establishment not found");
        }
        List<Employee> employees = employeeRepository.findAllByEstablishment(establishment.get());
        // Usar uma fila para processar os funcionários
        Queue<Employee> queue = new LinkedBlockingQueue<>(employees);

        // Usar uma pilha para armazenar os resultados temporariamente
        Stack<EmployeeRelacionamento> stack = new Stack<>();

        while (!queue.isEmpty()) {
            Employee employee = queue.poll();
            List<ServiceResponse> services = employeeServService.findByEmployee(employee);
            EmployeeRelacionamento employeeRelacionamento = new EmployeeRelacionamento(
                    employee.getId(), employee.getName(), employee.getEmail(), employee.getPassword(),
                    employee.getImgUrl(), employee.getEstablishment(), employee.getEmployeeType(), services
            );
            stack.push(employeeRelacionamento);
        }

        // Converter a pilha de volta para uma lista
        List<EmployeeRelacionamento> employeeRelacionamentos = new ArrayList<>();
        while (!stack.isEmpty()) {
            employeeRelacionamentos.add(stack.pop());
        }

        return employeeRelacionamentos;


        } catch (Exception e) {
            throw new RuntimeException("Error");
    }
    }

    public List<EmployeeRelacionamento> findAllByEstablishmentIds(List<Integer> establishmentIds) {
        List<EmployeeRelacionamento> allEmployees = new ArrayList<>();

        for (Integer establishmentId : establishmentIds) {
            List<EmployeeRelacionamento> employeesForEstablishment = findAllByEstablishment(establishmentId);
            allEmployees.addAll(employeesForEstablishment);
        }

        return allEmployees;
    }
}
