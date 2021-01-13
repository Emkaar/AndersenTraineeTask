package GamesCollection.repo;

import GamesCollection.model.collection.GameCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameCollectionCrudRepository extends CrudRepository<GameCollection, Long> {
    public List<GameCollection> getAllByUser_Username(String name);
    public GameCollection findByNameAndUser_Username(String collectionName, String username);
}
