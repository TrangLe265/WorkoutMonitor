package fi.haagahelia.cyclyingapp.web;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;


import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.List; 

import fi.haagahelia.cyclyingapp.domain.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ActivityService service; 

    // Endpoint for uploading activities, requires authentication
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadActivity(@RequestPart("file") MultipartFile file, HttpServletRequest request, Principal principal){
        try { 
            
            String username = principal.getName();
            System.out.println("Uploading file for user: " + username);

            System.out.println("Starting file upload: " + file.getOriginalFilename());
            // Call the service to upload activities from the provided file
            List<Activity> activities = service.uploadActivities(file);
            System.out.println("Successfully uploaded activities.");
            
            return ResponseEntity.ok(activities); // Return the number of uploaded activities
        } catch (IOException e) {
            // Log the error and return a meaningful response
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing error: " + e.getMessage());

        } catch (Exception e) {
            // Catch any other exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured: " + e.getMessage());
        }
    }
    
        
    }
