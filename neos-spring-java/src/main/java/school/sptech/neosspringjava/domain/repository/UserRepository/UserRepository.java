// package school.sptech.neosspringjava.domain.repository.UserRepository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import school.sptech.neosspringjava.domain.model.user.User;
// import school.sptech.neosspringjava.domain.model.client.Client;
// import school.sptech.neosspringjava.domain.model.employee.Employee;

// public interface UserRepository extends JpaRepository<User, Integer> {

//     default Optional<Object> findByEmail(String email) {
//         Optional<Client> client = findClientByEmail(email);
//         Optional<Employee> employee = findEmployeeByEmail(email);

//         if (client.isPresent()) {
//             return Optional.of(client.get());
//         } else if (employee.isPresent()) {
//             return Optional.of(employee.get());
//         } else {
//             return Optional.empty();
//         }
//     }

//     Optional<Client> findClientByEmail(String email);
//     Optional<Employee> findEmployeeByEmail(String email);

//     Client findClientByEmailAndPassword(String email, String password);
//     Employee findEmployeeByEmailAndPassword(String email, String password);

//     boolean existsClientByEmail(String email);
//     boolean existsEmployeeByEmail(String email);
// }
