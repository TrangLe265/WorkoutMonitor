package fi.haagahelia.cyclyingapp.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //bean allowed methods to be registed as Spring-managed bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http //configure authorizations for http request
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers( "/","/login","/error","/api/v1/users/dashboard").permitAll()
                    .anyRequest().authenticated() //but any other pages needs logging in 
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard",true)
                )
                .logout(logout -> logout
                    .logoutSuccessUrl("/")
                    .permitAll()
                ); 

                return http.build(); 
                //All logout link to ur front end <a href="/logout">Logout</a>
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails defaultUser = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("ADMIN")
            .build();
        
        return new InMemoryUserDetailsManager(defaultUser); // Return the user details service
    }

}
