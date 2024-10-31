package fi.haagahelia.cyclyingapp.web;

import org.springframework.web.bind.annotation.GetMapping;

public class UploadController {
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload"; // This should return the name of your HTML upload page
    }

}
