package fi.haagahelia.cyclyingapp.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean //bean allowed methods to be registed as Spring-managed bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http //configure authorizations for http request
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/index", "/login").permitAll(); 
                    auth.anyRequest().authenticated(); //but any other pages needs logging in 
                })
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashbpard", true)
                    .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard",true)
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                )
                .build(); 
                //All logout link to ur front end <a href="/logout">Logout</a>
    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        // Create an admin user with username "admin" and password "password"
        manager.createUser(User.withUsername("admin")
                .password("{noop}password") // {noop} indicates no password encoding
                .roles("ADMIN") // Set the role for the user
                .build());
        
        return manager; // Return the user details service
    }

}
