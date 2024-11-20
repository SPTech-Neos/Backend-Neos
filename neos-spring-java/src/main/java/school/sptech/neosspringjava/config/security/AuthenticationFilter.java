package school.sptech.neosspringjava.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import school.sptech.neosspringjava.config.security.jwt.GerenciadorTokenJwt;
import school.sptech.neosspringjava.service.AuthenticationService;

import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    private final AuthenticationService authenticationService;
    private final GerenciadorTokenJwt jwtTokenManager;

    public AuthenticationFilter(AuthenticationService authenticationService, GerenciadorTokenJwt jwtTokenManager) {
        this.authenticationService = authenticationService;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestPath = request.getRequestURI();
        String x = "x";
        if (requestPath.startsWith("/public") || requestPath.equals("/employees")|| requestPath.equals("/clients") || requestPath.contains("login") || requestPath.equals("/register") || x == "x") {
            filterChain.doFilter(request, response);
            return;
        }

        String username = null;
        String jwtToken = null;

        String requestTokenHeader = request.getHeader("Authorization");

        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            System.out.println("sapora" + jwtToken);

            try {
                username = jwtTokenManager.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException exception) {

                System.out.println("caiu no catch");

                LOGGER.info("[FALHA AUTENTICACAO] - Token expirado, usuario: {} - {}",
                        exception.getClaims().getSubject(), exception.getMessage());

                LOGGER.trace("[FALHA AUTENTICACAO] - stack trace: %s", exception);

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        System.out.println(username + "username");
        System.out.println(SecurityContextHolder.getContext().getAuthentication() + "esse trem aqui");
        System.out.println(request + "o tal do request");
        System.out.println(response + "O tal do response");
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            addUsernameInContext(request, username, jwtToken);
        }
        filterChain.doFilter(request, response);
    }
            private void addUsernameInContext(HttpServletRequest request, String username, String jwtToken) {
                System.out.println("AQUI Ó"+username);
                UserDetails userDetails = authenticationService.loadUserByUsername(username);

                if (jwtTokenManager.validateToken( jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new
                            UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            usernamePasswordAuthenticationToken
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext( ).setAuthentication( usernamePasswordAuthenticationToken);
        }
    }
}
