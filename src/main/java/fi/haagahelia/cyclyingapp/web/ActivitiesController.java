package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fi.haagahelia.cyclyingapp.domain.ActivityRepository;
import fi.haagahelia.cyclyingapp.domain.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ActivitiesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private ActivityRepository activityRepository; 

    @GetMapping("/showAll")
    public String showAll(@RequestParam String param) {
        return "All activities"; 
    }
    
}
