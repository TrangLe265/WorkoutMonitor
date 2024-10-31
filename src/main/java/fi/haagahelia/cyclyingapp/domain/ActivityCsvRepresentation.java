package fi.haagahelia.cyclyingapp.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder     
public class ActivityCsvRepresentation {
    @CsvBindByName(column = "Activity Date" )
    private String date; 
    @CsvBindByName(column = "Activity Type" )
    private String activityType;
    @CsvBindByName(column = "Moving Time" )
    private float movingTime;
    @CsvBindByName(column = "Distance" )
    private float distance; 
    @CsvBindByName(column = "Average Speed" )
    private float aveSpeed;
    
}
