package fi.haagahelia.cyclyingapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id; 
    
    private String username; 
    private String password;
    private String role;
    
    @OneToMany(mappedBy = "user")
    private Set<Activity> activities;  

}

