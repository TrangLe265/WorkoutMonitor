package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

import fi.haagahelia.cyclyingapp.domain.Activity;
import fi.haagahelia.cyclyingapp.domain.ActivityRepository;
import fi.haagahelia.cyclyingapp.domain.UserRepository;


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
        return activityRepository.findAll(); // Return all activities in JSON format
    }

    // RESTful service to get activity by id
    @RequestMapping(value="/activities/id/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Activity> findActivityById(@PathVariable("id") Long activityId) {	
    	return activityRepository.findByActivityId(activityId);
    } 
    
    @RequestMapping(value ="/activities/type/{activityType}", method=RequestMethod.GET)
    public @ResponseBody List<Activity> findActivitiesByType(@PathVariable("activityType") String activityType) {
        return  activityRepository.findByActivityType(activityType);
    }

    @RequestMapping(value = "/activities/delete/id/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteActivity(@PathVariable("id") Long activityId) {
        activityRepository.deleteById(activityId);
        return ResponseEntity.ok("Activity delete successfully"); 
    }
        
}
