package school.sptech.neosspringjava.api.dtos.usersDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;

import java.util.Collection;
import java.util.List;

public class UsersDetailsDto implements UserDetails {
    private final String name;
    private final String email;
    private final  String password;

    public UsersDetailsDto(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.password = client.getPassword();
    }

    public UsersDetailsDto (Employee employee){
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
    }

    public String getName() {
        return name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
