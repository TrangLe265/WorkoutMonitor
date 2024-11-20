package fi.haagahelia.cyclyingapp.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findAll();
    List<Activity> findByActivityType(String activityType);
    List<Activity> findByUser(User user);
    Optional<Activity> findByActivityId(Long activityId);
    
}