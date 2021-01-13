package GamesCollection.repo;

import GamesCollection.model.users.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
