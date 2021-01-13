package GamesCollection.service;

import GamesCollection.model.games.Game;
import GamesCollection.repo.GameCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameDataService {

    private GameCrudRepository gameCrudRepository;

    @Autowired
    public GameDataService(GameCrudRepository gameCrudRepository) {
        this.gameCrudRepository = gameCrudRepository;
    }

    public void addGame(Game game){
        gameCrudRepository.save(game);
    }

    public void deleteGame(Game game){
        gameCrudRepository.delete(game);
    }

    public List<Game> getAll(){
        return (List<Game>) gameCrudRepository.findAll();
    }

    public void deleteAll(){
        gameCrudRepository.deleteAll();
    }

    public Game getById(Integer id){
        return gameCrudRepository.getById(id);
    }
}
