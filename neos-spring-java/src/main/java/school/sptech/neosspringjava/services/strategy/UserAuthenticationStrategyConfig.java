package school.sptech.neosspringjava.services.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAuthenticationStrategyConfig {

    @Bean
    public UserAuthenticationStrategy userAuthenticationStrategy() {
        return new EmailPasswordAuthenticationStrategy();
    }
}