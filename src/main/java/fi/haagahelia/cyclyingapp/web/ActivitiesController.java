package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import fi.haagahelia.cyclyingapp.domain.*; 

//All restful services for activities in this filel
@Controller
@RequestMapping("/api") 
public class ActivitiesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private ActivityRepository activityRepository; 

    //Restful service to get all activities
    @RequestMapping(value="/activities", method = RequestMethod.GET)
    public @ResponseBody List<Activity> showAll() {
        return activityRepository.findAll(); 
    }

    //Restful service to get all activities by the curreny user
    @RequestMapping(value="/activities/byUser/{userName}", method = RequestMethod.GET)
    public @ResponseBody List<Activity> showActivitiesByUser(@PathVariable("userName") String userName,Principal principal ) {
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());   
        if (currentUser.isPresent()) {
            User user = currentUser.get(); 
            if (user.getUsername().equals(userName)) {
                return activityRepository.findByUser(user); 
            } else {
                throw new IllegalArgumentException("User not found");
            }
        }else {
            throw new IllegalArgumentException("User not found");
        }
    }   
    

    // RESTful service to get activity by id
    @RequestMapping(value="/activities/id/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Activity> findActivityById(@PathVariable("id") Long activityId) {	
    	return activityRepository.findByActivityId(activityId);
    } 
    
    //Restful service to get activities by type
    @RequestMapping(value ="/activities/type/{activityType}", method=RequestMethod.GET)
    public @ResponseBody List<Activity> findActivitiesByType(@PathVariable("activityType") String activityType) {
        return  activityRepository.findByActivityType(activityType);
    }

    //Restful servive to delete
    @RequestMapping(value = "/activities/delete/id/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long activityId) {
        try {
            if (activityRepository.existsById(activityId)){
                activityRepository.deleteById(activityId);
                return ResponseEntity.ok("Activity delete successfully"); 
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting activity");
        }
        
    }
        
}
