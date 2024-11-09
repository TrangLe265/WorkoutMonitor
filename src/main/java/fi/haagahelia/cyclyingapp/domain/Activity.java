package fi.haagahelia.cyclyingapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {

    @Id
    @GeneratedValue
    private Long activityId;
    private String date; 
    private String activityType;
    private float movingTime;
    private float distance; 
    private float aveSpeed;
   @ManyToOne
    @JoinColumn(name = "user_id") // this name does not have to match the id field in the User class
    private User user; 

}

