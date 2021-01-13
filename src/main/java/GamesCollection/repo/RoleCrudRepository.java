package GamesCollection.repo;

import GamesCollection.model.users.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepository extends CrudRepository<Role, Long> {
}
