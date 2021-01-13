package GamesCollection.repo;

import GamesCollection.model.games.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameCrudRepository extends CrudRepository<Game, Integer> {
    List<Game>findAllByNumberOfPlayers(int numberOfPlayers);
    int countAllByIdBefore(Integer id);
    Game getById(Integer id);

}
