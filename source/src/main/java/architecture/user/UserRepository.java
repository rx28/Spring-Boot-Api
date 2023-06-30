package architecture.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsByEmailOrUsername(String email, String username);

    @Query(value = "{$and: [{'_id': {$ne: ?0}}, {$or: [{'email': ?1}, {'username': ?2}]}]}", exists = true)
    boolean existsByEmailOrUsername(UUID id, String email, String username);
}
