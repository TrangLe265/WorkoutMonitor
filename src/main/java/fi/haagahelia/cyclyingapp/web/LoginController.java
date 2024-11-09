package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.haagahelia.cyclyingapp.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository; 

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
}

