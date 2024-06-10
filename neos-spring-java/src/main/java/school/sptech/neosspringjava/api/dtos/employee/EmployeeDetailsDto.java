package school.sptech.neosspringjava.api.dtos.employee;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import school.sptech.neosspringjava.domain.model.client.Client;
import school.sptech.neosspringjava.domain.model.employee.Employee;
import school.sptech.neosspringjava.domain.model.employeeType.EmployeeType;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class EmployeeDetailsDto implements UserDetails {
    private final String name;
    private final String email;
    private final  String password;
    private final String imgUrl;
    private final Establishment establishment;
    private final EmployeeType employeeType;

    public EmployeeDetailsDto(Employee employee) {
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.password = employee.getPassword();
        this.imgUrl = employee.getImgUrl();
        this.establishment = employee.getEstablishment();
        this.employeeType = employee.getEmployeeType();
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

