package school.sptech.neosspringjava.api.dtos.clientDto;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.neosspringjava.domain.model.client.Client;

import java.util.Collection;
import java.util.List;

public class ClientDetailsDto implements UserDetails {
    private final String name;
    private final String email;
    private final  String password;

    public ClientDetailsDto(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.password = client.getPassword();
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
