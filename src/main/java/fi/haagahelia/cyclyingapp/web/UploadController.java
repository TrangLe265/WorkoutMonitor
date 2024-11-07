package fi.haagahelia.cyclyingapp.web;

import org.springframework.web.bind.annotation.GetMapping;

public class UploadController {
    @GetMapping("/dashboard")
    public String uploadPage() {
        return "dashboard"; // This should return the name of your HTML upload page
    }

}
