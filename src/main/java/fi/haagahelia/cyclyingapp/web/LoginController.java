package fi.haagahelia.cyclyingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.cyclyingapp.domain.User;
import fi.haagahelia.cyclyingapp.domain.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model,String error, String logout){

        if (error != null){
            model.addAttribute("error", true); 
            model.addAttribute("errorMessage", "Invalid username or password"); 
        }

        return "login";
    }

    @GetMapping("/dashboard")
    public String home(){
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



