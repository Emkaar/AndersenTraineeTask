package GamesCollection.utils;

import GamesCollection.connection.DBConnector;
import GamesCollection.games.Game;
import GamesCollection.repository.GameRepository;
import GamesCollection.repository.Repository;
import java.sql.SQLException;

public class DBGameUtil {

    private static Repository gameRepository = new GameRepository(DBConnector.getConnection());

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
            System.out.println(gameRepository.getAll());
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

    public static void closeConnection(){
            DBConnector.closeConnections();
    }
}
