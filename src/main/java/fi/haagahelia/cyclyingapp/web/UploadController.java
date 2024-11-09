package fi.haagahelia.cyclyingapp.web;

import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;

import fi.haagahelia.cyclyingapp.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.security.Principal;

@Controller
public class UploadController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/dashboard")
    public String uploadPage(Model model, Principal principal) {
        if (principal == null){
            model.addAttribute("error", "User not authenticated");
        }

        String username = principal.getName(); 
        boolean userExist = userRepository.findByUsername(username).isPresent();

        if (!userExist){
            model.addAttribute("error", "User data not found");
            return "error"; // Redirect to an error page or show an error message
        }

        System.out.println("Current user is :" + username);

        return "dashboard";
    }

}
