package example.storecard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/storecards/**")
                .hasRole("CARD-OWNER")
                .and()
                .csrf().disable()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder){
        User.UserBuilder users = User.builder();
        UserDetails sarah = users
                .username("sarah1")
                .password(passwordEncoder.encode("sarah1"))
                .roles("CARD-OWNER")
                .build();

        UserDetails bobby = users
                .username("bobby2")
                .password(passwordEncoder.encode("bobby"))
                .roles("NON-OWNER")
                .build();

        UserDetails user = users
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("CARD-OWNER")
                .build();

        return new InMemoryUserDetailsManager(sarah, bobby, user);
    }

}
