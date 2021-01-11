package GamesCollection.service;

import GamesCollection.games.Game;
import GamesCollection.repository.GameCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService {

    private GameCrudRepository gameCrudRepository;

    @Autowired
    public UserDataService(GameCrudRepository gameCrudRepository) {
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
}
