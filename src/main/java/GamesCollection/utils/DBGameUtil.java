package GamesCollection.utils;

import GamesCollection.games.Game;
import GamesCollection.repository.GameRepository;
import GamesCollection.repository.Repository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

public class DBGameUtil {
    public static final String DB_USER_NAME = "root";
    public static final String DB_PASSWORD = "45673756";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/games";
    public static final String TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static HikariDataSource ds;

    private static Repository gameRepository;
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL + TIME_ZONE);
        config.setPassword(DB_PASSWORD);
        config.setUsername(DB_USER_NAME);
        config.setMaximumPoolSize(10);
        ds = new HikariDataSource(config);
        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(DB_URL + TIME_ZONE, DB_USER_NAME, DB_PASSWORD);
//            connection.setAutoCommit(false);
            gameRepository = new GameRepository(ds.getConnection());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        catch (ClassNotFoundException ex){
//            ex.printStackTrace();
//        }
    }

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
            ds.close();
    }
}
