package fi.haagahelia.cyclyingapp.domain;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.Buffer;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;

    public Integer uploadActivities(MultipartFile file) throws IOException {
        if(repository == null){
            throw new IllegalStateException("ActivityRepository is not injected"); 
        }

        System.out.println("Starting uploadActivities method");
        Set<Activity> activities = parseCsv(file); 
        repository.saveAll(activities); 
        return activities.size(); 
    }

    private Set<Activity> parseCsv(MultipartFile file) throws IOException{
        System.out.println("Starting parseCsv method");
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            //map the headers, define what columns do we want to read from the csv file
            HeaderColumnNameMappingStrategy<ActivityCsvRepresentation> strategy = new HeaderColumnNameMappingStrategy<>(); 
            strategy.setType(ActivityCsvRepresentation.class); 

            CsvToBean<ActivityCsvRepresentation> csvToBean = 
                new CsvToBeanBuilder<ActivityCsvRepresentation>(reader)
                .withMappingStrategy(strategy)
                .withIgnoreEmptyLine(true)
                .withIgnoreLeadingWhiteSpace(true)
                .build(); 

            Set<Activity> activities = csvToBean.parse()
                .stream()
                .map(csvLine -> Activity.builder()
                        .date(csvLine.getDate())
                        .activityType(csvLine.getActivityType())
                        .movingTime(csvLine.getMovingTime())
                        .distance(csvLine.getDistance())
                        .aveSpeed(csvLine.getAveSpeed())
                        .build()
               )
                .collect(Collectors.toSet()); 
                System.out.println("Finished parsing CSV file");
            return activities;

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            throw new IOException("Error reading CSV file", e); // Proper error handling
        }
        
    }

}
