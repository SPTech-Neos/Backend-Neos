package school.sptech.neosspringjava.api.dtos.usersDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeLogin;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Employee;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UsersDetailsDto implements UserDetails {
    private final String name;
    private final String email;
    private final String password;

    public UsersDetailsDto(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
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
