package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.cyclyingapp.domain.User;
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

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard"; 
    }
}

    /*@GetMapping({"/login"})
    public String home(Model model, @AuthenticationPrincipal OidcUser principal){
        if (principal != null){
            String oauth2Id = principal.getSubject();
            String email = principal.getAttribute("email");
            String name = principal.getAttribute("name");
            String picture = principal.getAttribute("picture");

            User user = userRepository.findByoauth2Id(oauth2Id).orElse(null); 

            if (user == null){
                user = new User(); 
                user.setOauth2Id(oauth2Id);
                user.setEmail(email);
                user.setName(name);
                user.setPicture(picture);
                userRepository.save(user); 
                System.out.println("New user saved");
            }

            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("picture", picture);

            return "upload";
    }
        return "login"; 
    }*/



