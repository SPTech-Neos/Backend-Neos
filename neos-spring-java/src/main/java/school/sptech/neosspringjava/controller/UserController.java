package school.sptech.neosspringjava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.neosspringjava.modal.User;
import school.sptech.neosspringjava.services.UserAuthenticationStrategy;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> lstUsers = new ArrayList<>();
    private UserAuthenticationStrategy authenticationStrategy;

    public UserController(UserAuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }

    /*
     * Metodo que retorna todos os usuarios
     * 
     * @return lista de usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(200).body(lstUsers);
    }

    /*
     * Metodo que retorna um usuario pelo email e senha
     * 
     * @Param email email do usuario
     * 
     * @Param password senha do usuario
     */
    @GetMapping("login/{email}/{password}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email,
            @PathVariable("password") String password) {
        for (User u : lstUsers) {
            if (authenticationStrategy.authenticate(u, email, password)) {
                return ResponseEntity.status(200).body(u);
            }
        }
        return ResponseEntity.status(404).build();
    }

    /*
     * Metodo que cria um novo usuario
     * 
     * ~ @return usuario adicionado
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        lstUsers.add(user);
        return ResponseEntity.status(201).body(user);
    }

    /*
     * Metodo que atualiza um usuario pelo id
     * 
     * ^ @param id id do usuario
     * 
     * ^ @param updatedUser usuario com os dados atualizados
     * 
     * ~ @return usuario atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User updatedUser) {
        for (int i = 0; i < lstUsers.size(); i++) {
            User user = lstUsers.get(i);
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                return ResponseEntity.status(200).body(user);
            }
        }
        return ResponseEntity.status(404).build();
    }

    /*
     * Metodo que deleta um usuario pelo id
     * 
     * ^ @param id id do usuario
     * 
     * ~ @return usuario deletado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        for (User u : lstUsers) {
            if (u.getId().equals(id)) {
                lstUsers.remove(u);
                return ResponseEntity.status(200).body(u);
            }
        }
        return ResponseEntity.status(404).build();
    }

    public void setAuthenticationStrategy(UserAuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }
}