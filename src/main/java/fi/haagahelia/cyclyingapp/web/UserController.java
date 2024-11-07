package fi.haagahelia.cyclyingapp.web;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fi.haagahelia.cyclyingapp.domain.ActivityService;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.security.Principal;

/*@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final ActivityService service; 

    @PostMapping(value = "/upload",  consumes = {"multipart/form-data"})
    public ResponseEntity<Integer> uploadActivity(
        @RequestPart("file")MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(service.uploadActivities(file)); 
    }


}*/

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final ActivityService service; 

    // Endpoint for uploading activities, requires authentication
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadActivity(@RequestPart("file") MultipartFile file, HttpServletRequest request, Principal principal){
        try { 
            {/*CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            System.out.println("CSRF Token: " + csrfToken.getToken());*/}

            String username = principal.getName();
            System.out.println("Uploading file for user: " + username);

            System.out.println("Starting file upload: " + file.getOriginalFilename());
            // Call the service to upload activities from the provided file
            Integer uploadedCount = service.uploadActivities(file);
            System.out.println("Successfully uploaded " + uploadedCount + " activities.");
            
            return ResponseEntity.ok(uploadedCount); // Return the number of uploaded activities
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
