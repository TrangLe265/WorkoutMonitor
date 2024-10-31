package fi.haagahelia.cyclyingapp.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<Integer> uploadActivity(
        @RequestPart("file") MultipartFile file
    ) throws IOException {
        // Call the service to upload activities from the provided file
        Integer uploadedCount = service.uploadActivities(file);
        return ResponseEntity.ok(uploadedCount); // Return the number of uploaded activities
    }
}