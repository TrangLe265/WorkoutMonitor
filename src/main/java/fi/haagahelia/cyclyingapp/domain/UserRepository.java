package fi.haagahelia.cyclyingapp.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByoauth2Id(String oauth2Id); 
}
