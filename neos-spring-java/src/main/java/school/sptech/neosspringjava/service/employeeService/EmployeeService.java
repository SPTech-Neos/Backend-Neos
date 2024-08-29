package school.sptech.neosspringjava.service.employeeService;

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
import school.sptech.neosspringjava.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRequest;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeTokenDto;
import school.sptech.neosspringjava.api.mappers.employeeMapper.EmployeeMapper;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.repository.employeeRepository.EmployeeRepository;
import school.sptech.neosspringjava.service.EmployeeServService.EmployeeServService;
import school.sptech.neosspringjava.service.employeeTypeService.EmployeeTypeService;
import school.sptech.neosspringjava.service.establishmentService.EstablishmentService;
import school.sptech.neosspringjava.service.localService.LocalService;
import school.sptech.neosspringjava.service.phoneService.PhoneService;
import school.sptech.neosspringjava.service.statusService.StatusService;

@Service
@RequiredArgsConstructor
public class EmployeeService {

   private final EmployeeRepository employeeRepository;
   private final EstablishmentService establishmentService;
   private final EmployeeTypeService eTypeService;
   private final PasswordEncoder passwordEncoder;
   private final EmployeeServService employeeServService;
   private final LocalService lService;
   private final PhoneService pService;
   private final StatusService sService;

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

    public Employee partialUpdate(Map<String, Object> updates, Integer id) {
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
            employee.setEstablishment(establishmentService.findById(fkEstablishment));
        }

        if (updates.containsKey("employeeType")) {
            Integer employeeType = (Integer) updates.get("employeeType");
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

        e.setStatus(sService.findStatusByName("Inativo"));

        return e;
    }

    public Employee reactivate(Integer id){
        Employee e = findById(id);

        e.setStatus(sService.findStatusByName("Ativo"));

        return e;
    }
    
//    public List<EmployeeRelacionamento> findAllByEstablishment(Integer fkEstablishment) {
//    try {
//        Optional<Establishment> establishment = establishmentRepository.findById(fkEstablishment);
//        if (establishment.isEmpty()) {
//            throw new NullPointerException("Establishment not found");
//        }
//        List<Employee> employees = employeeRepository.findAllByEstablishment(establishment.get());
//        // Usar uma fila para processar os funcionários
//        Queue<Employee> queue = new LinkedBlockingQueue<>(employees);
//
//        // Usar uma pilha para armazenar os resultados temporariamente
//        Stack<EmployeeRelacionamento> stack = new Stack<>();
//
//        while (!queue.isEmpty()) {
//            Employee employee = queue.poll();
//            List<ServiceResponse> services = employeeServService.findByEmployee(employee);
//            EmployeeRelacionamento employeeRelacionamento = new EmployeeRelacionamento(
//                    employee.getId(), employee.getName(), employee.getEmail(), employee.getPassword(),
//                    employee.getImgUrl(), employee.getEstablishment(), employee.getEmployeeType(), services
//            );
//            stack.push(employeeRelacionamento);
//        }
//
//        // Converter a pilha de volta para uma lista
//        List<EmployeeRelacionamento> employeeRelacionamentos = new ArrayList<>();
//        while (!stack.isEmpty()) {
//            employeeRelacionamentos.add(stack.pop());
//        }
//
//        return employeeRelacionamentos;
//
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error");
//    }
//    }
//
//    public List<EmployeeRelacionamento> findAllByEstablishmentIds(List<Integer> establishmentIds) {
//        List<EmployeeRelacionamento> allEmployees = new ArrayList<>();
//
//        for (Integer establishmentId : establishmentIds) {
//            List<EmployeeRelacionamento> employeesForEstablishment = findAllByEstablishment(establishmentId);
//            allEmployees.addAll(employeesForEstablishment);
//        }
//
//        return allEmployees;
//    }
}
