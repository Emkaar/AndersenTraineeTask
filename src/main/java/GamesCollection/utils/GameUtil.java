package GamesCollection.utils;

import GamesCollection.connection.DbConnector;
import GamesCollection.games.Game;
import GamesCollection.repository.GameDbRepository;
import GamesCollection.repository.GameHibernateRepository;
import GamesCollection.repository.Repository;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class GameUtil {

    private static Repository gameRepository = new GameHibernateRepository();

    public static boolean addGame(Game game){
        try {
            return gameRepository.addGame(game);
        } catch (SQLException exception) {
            return false;
        }
    }

    public static boolean deleteGame(String name){
        try {
            return gameRepository.deleteGame(name);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean showAll(){
        try {
            if (gameRepository.getAll().isEmpty()){
                return false;
            }
            Set<Game> gameSet = gameRepository.getAll();
            for(Game game : gameSet){
                System.out.println(game);
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }


    public static boolean deleteAllGames() {
        try {
            if(gameRepository.deleteAllGames()){
                return true;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void exit() {
        gameRepository.exit();
    }
}
