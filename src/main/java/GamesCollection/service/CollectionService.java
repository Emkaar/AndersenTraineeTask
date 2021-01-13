package GamesCollection.service;

import GamesCollection.model.collection.GameCollection;
import GamesCollection.model.games.Game;
import GamesCollection.model.users.User;
import GamesCollection.repo.GameCollectionCrudRepository;
import GamesCollection.repo.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    private GameCollectionCrudRepository gameCollectionCrudRepository;
    private UserCrudRepository userCrudRepository;

    @Autowired
    public CollectionService(GameCollectionCrudRepository gameCollectionCrudRepository, UserCrudRepository userCrudRepository) {
        this.gameCollectionCrudRepository = gameCollectionCrudRepository;
        this.userCrudRepository = userCrudRepository;
    }

    public boolean saveCollection(String userName, String collectionName){
        GameCollection newCollection = gameCollectionCrudRepository.findByNameAndUser_Username(collectionName, userName);
        if(newCollection != null){
            return false;
        }
        User userFromDb = userCrudRepository.findByUsername(userName);
        newCollection = new GameCollection();
        newCollection.setName(collectionName);
        newCollection.setUser(userFromDb);
        gameCollectionCrudRepository.save(newCollection);
        return true;
    }

    public List<GameCollection> getCollectionsByUser(String username){
        return gameCollectionCrudRepository.getAllByUser_Username(username);
    }

    public List<Game> getGamesFromCollection(Long id) {
        return gameCollectionCrudRepository.findById(id).orElse(new GameCollection()).getGameList();
    }

    public GameCollection getCollectionById(Long id) {
        return gameCollectionCrudRepository.findById(id).orElse(new GameCollection());
    }
}
