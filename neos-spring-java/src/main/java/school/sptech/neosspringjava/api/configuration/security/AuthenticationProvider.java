package school.sptech.neosspringjava.api.configuration.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
// import school.sptech.neosspringjava.service.client.authentication.AuthenticationService;
import school.sptech.neosspringjava.service.user.authentication.AuthenticationService;

public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationProvider(AuthenticationService auth, PasswordEncoder passwordEncoder) {
        this.authenticationService = auth;
        this.passwordEncoder = passwordEncoder;
    }

    

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String[] credentials = authentication.getName().split(";");
        System.out.println("AuthenticationProvider >>credentials"+credentials[0]);
        System.out.println("AuthenticationProvider >>credentials"+credentials[1]);

        final String username = credentials[0];
        final String type = credentials[1];
        final String password = authentication.getCredentials().toString();


    
        UserDetails userDetails = null;
        if (isClient(type)) {
            userDetails = this.authenticationService.loadUserByUsername(username+";"+type);
        } else if (isEmployee(type)) {
            userDetails = this.authenticationService.loadUserByUsername(username+";"+type);
        }
        
        if (userDetails != null && this.passwordEncoder.matches(password, userDetails.getPassword())){
            System.out.println("AuthenticationProvider >> userDetails>>"+userDetails);
            System.out.println("AuthenticationProvider >> userDetails.getAuthorities()>>"+userDetails.getUsername());

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
    }
    
    private boolean isClient(String type) {
        return "client".equals(type);
    }
    
    private boolean isEmployee(String type) {
        System.out.println("isemployee"+("employee".equals(type)));
        System.out.println("isemployee"+(type));
        return "employee".equals(type);

    }    
    

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
