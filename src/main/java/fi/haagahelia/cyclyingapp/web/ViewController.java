package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fi.haagahelia.cyclyingapp.domain.ActivityRepository;
import fi.haagahelia.cyclyingapp.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;
     @Autowired 
    private ActivityRepository activityRepository; 


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

    @GetMapping("/activityList")
    public String activityList(Model model) {
        try {
            model.addAttribute("activities", activityRepository.findAll()); 
            return "activitylist"; 
        } catch (Exception e) {
            // Log the exception (you can use a logger here)
            e.printStackTrace(); // For debugging purposes
            model.addAttribute("error", "An error occurred while fetching activities.");
            return "error"; // Return to an error page
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable("id") Long activityId, Model model) {
        try {
            activityRepository.deleteById(activityId);
            return "redirect:/activityList";
        } catch (Exception e) {
            // Log the exception (you can use a logger here)
            e.printStackTrace(); // For debugging purposes
            model.addAttribute("error", "An error occurred while deleting the activity.");
            return "error"; // Return to an error page
        }
    }
    


}

