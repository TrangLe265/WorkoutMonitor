package fi.haagahelia.cyclyingapp.web;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fi.haagahelia.cyclyingapp.domain.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;
     @Autowired 
    private ActivityRepository activityRepository; 

    //login view
    @GetMapping({"/login"})
    public String login(Model model, HttpServletRequest request){
        String error = request.getParameter("error");
        String logout = request.getParameter("logout");

        if (error != null){
            model.addAttribute("error", true); 
            model.addAttribute("errorMessage", "Invalid username or password"); 
        }
        if (logout != null){
            model.addAttribute("logout", true); 
            model.addAttribute("logoutMessage", "You have been logged out successfully"); 
        }

        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName()); 
        model.addAttribute(("csrfToken"), csrfToken);
        
        return "login";
    }

    //show all activties
    @GetMapping("/activityList")
    public String activityList(Model model, Principal principal) {
        try {
            Optional<User> currentUser = userRepository.findByUsername(principal.getName());
            if (currentUser.isPresent()) {
                User user = currentUser.get();
                model.addAttribute("activities", activityRepository.findByUser(user));
            } else {
                throw new IllegalArgumentException("User not found");
            }
            return "activitylist"; 
        } catch (Exception e) {
            e.printStackTrace(); 
            model.addAttribute("error", "An error occurred while fetching activities.");
            return "error"; 
        }
    }

    //delete activity by Id
    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable("id") Long activityId, Model model) {
        try {
            activityRepository.deleteById(activityId);
            return "redirect:/activityList";
        } catch (Exception e) {
            e.printStackTrace(); 
            model.addAttribute("error", "An error occurred while deleting the activity.");
            return "error"; 
        }
    }

    @GetMapping("/add")
    public String addActivity(Model model) {
        model.addAttribute("activity", new Activity()); 
        return "addactivity";
    }

    @PostMapping("/save")
    public String saveActivity(Activity activity, Principal principal, Model model) {
        try{
                Optional<User> currentUser = userRepository.findByUsername(principal.getName());
            if (currentUser.isPresent()) {
                activity.setUser(currentUser.get());
                activityRepository.save(activity);  
            }else {
                throw new IllegalArgumentException("User not found");
            }

            return "redirect:/activityList"; 
        } catch (Exception e){
            e.printStackTrace(); 
            model.addAttribute("error", "An error occurred while saving the activity.");
            return "error";
        }
        
    }
    
    
    
}

