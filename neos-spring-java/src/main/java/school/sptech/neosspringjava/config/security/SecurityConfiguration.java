package school.sptech.neosspringjava.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import school.sptech.neosspringjava.config.security.jwt.GerenciadorTokenJwt;
import school.sptech.neosspringjava.service.AuthenticationService;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String ORIGENS_PERMITIDAS = "*";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = new AntPathRequestMatcher[]{
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/error/**"),
            new AntPathRequestMatcher("/client/login"),
            // new AntPathRequestMatcher("/client/**"),
            new AntPathRequestMatcher("/clients", "POST"),
            new AntPathRequestMatcher("/employees"),
            new AntPathRequestMatcher("/employees/**"),
            new AntPathRequestMatcher("/establishments"),
            new AntPathRequestMatcher("/establishments/**"),

            new AntPathRequestMatcher("/addresses/**"), // apenas para testes daqui para baixo
            new AntPathRequestMatcher("/addresses"),
            new AntPathRequestMatcher("/locals/**"),
            new AntPathRequestMatcher("/locals"),
            new AntPathRequestMatcher("/product-types/**"),
            new AntPathRequestMatcher("/product-types"),
            new AntPathRequestMatcher("/products/**"),
            new AntPathRequestMatcher("/products"),
            new AntPathRequestMatcher("/ratings/**"),
            new AntPathRequestMatcher("/ratings"),
            new AntPathRequestMatcher("/employee-types/**"),
            new AntPathRequestMatcher("/employee-types"),
            new AntPathRequestMatcher("/service-categories/**"),
            new AntPathRequestMatcher("/service-categories"),
            new AntPathRequestMatcher("/service-types/**"),
            new AntPathRequestMatcher("/service-types"),
            new AntPathRequestMatcher("/services/**"),

            new AntPathRequestMatcher("/services"),
            new AntPathRequestMatcher("/employee-services/**"),
            new AntPathRequestMatcher("/employee-services"),
            new AntPathRequestMatcher("/schedulings"),
            new AntPathRequestMatcher("/schedulings/**"),
            new AntPathRequestMatcher("/payments"),
            new AntPathRequestMatcher("/payments/**"),
            new AntPathRequestMatcher("/dashboard"),
            new AntPathRequestMatcher("/dashboard/**"),
            new AntPathRequestMatcher("/orders/**"),
            new AntPathRequestMatcher("/markets/**"),
            new AntPathRequestMatcher("/phones/**"),
            new AntPathRequestMatcher("/addresses"),
            new AntPathRequestMatcher("/**/**"),

    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(URLS_PERMITIDAS)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.headers()
//                .frameOptions().disable()
//                .and()
//                .cors()
//                .configurationSource(request -> buildCorsConfiguration())
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers(URLS_PERMITIDAS)
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//        )
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .sessionManagement( )
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(new
                AuthenticationProvider(authenticationService, passwordEncoder()));
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationEntryPoint jwtAuthenticationEntryPointBean() {
        return new AuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationFilter jwtAuthenticationFilterBean() {
        return new AuthenticationFilter(authenticationService, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() {
        return new GerenciadorTokenJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracao = new CorsConfiguration();
        configuracao.applyPermitDefaultValues();
        configuracao.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.TRACE.name()));

        configuracao.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));

        UrlBasedCorsConfigurationSource origem = new UrlBasedCorsConfigurationSource();
        origem.registerCorsConfiguration("/**", configuracao);

        return origem;
    }
}