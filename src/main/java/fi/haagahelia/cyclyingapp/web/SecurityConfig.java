package fi.haagahelia.cyclyingapp.web;

import fi.haagahelia.cyclyingapp.domain.User;
import fi.haagahelia.cyclyingapp.domain.UserRepository; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import fi.haagahelia.cyclyingapp.CustomUserDetailsService;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService; 
    }

    @Bean //bean allowed methods to be registed as Spring-managed bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http //configure authorizations for http request
            .csrf(csrf -> csrf.ignoringRequestMatchers("/upload", "/api/*") // Disable CSRF for the upload endpoint
        )    
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers( "/","/login","/error").permitAll()
                    .requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/upload").authenticated()
                    .anyRequest().authenticated() //but any other pages needs logging in 
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard", true)
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutSuccessUrl("/login")
                    .permitAll()
                ); 

                return http.build(); 
                //All logout link to ur front end <a href="/logout">Logout</a>
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
    {/*@Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // Sample logic to add a user to the database
            User user = new User();

            String hashedPassword = passwordEncoder().encode("password");
            user.setUsername("trangle");
            user.setPassword(hashedPassword); 
			user.setRole("USER"); 
            userRepository.save(user);
            System.out.println("Sample user added to the database!");
        };
	}*/}
    

}
