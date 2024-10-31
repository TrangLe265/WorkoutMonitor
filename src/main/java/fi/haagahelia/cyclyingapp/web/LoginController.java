package fi.haagahelia.cyclyingapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    // Endpoint to display the login page
    @GetMapping("/login")
    public String login(Model model) {
        // You can add any attributes to the model if needed
        return "login"; // Return the name of the login view (e.g., login.html)
    }

    // Endpoint to handle login requests
    @PostMapping("/login")
    public String authenticate(String username, String password) {
        // This method will be called after a login attempt
        // Spring Security will handle the authentication
        // You can add custom logic here if needed
        return "redirect:/upload"; // Redirect to the dashboard after successful login
    }

}


