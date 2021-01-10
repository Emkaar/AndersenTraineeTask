package GamesCollection.service;

import GamesCollection.games.Game;
import GamesCollection.repository.GameCrudRepository;

import java.util.List;

public class UserDataService {

    private GameCrudRepository gameCrudRepository;

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
