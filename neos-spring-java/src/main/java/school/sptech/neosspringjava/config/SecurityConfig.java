// package school.sptech.neosspringjava.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.authorizeRequests()
//                 .antMatchers("/swagger-ui/**").authenticated() // Protege o acesso ao Swagger
//                 .anyRequest().permitAll() // Permite acesso a outras rotas sem autenticação
//                 .and()
//             .httpBasic(); // Usa autenticação básica HTTP
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.inMemoryAuthentication()
//                 .withUser("username").password("{noop}password").roles("USER"); // Adicione um usuário com senha
//     }
// }
