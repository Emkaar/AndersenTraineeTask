import GamesCollection.Games.SportGame;
import GamesCollection.Games.VideoGame;
import GamesCollection.repository.GameRepository;

import java.sql.*;
import java.util.Arrays;

public class DBMain {
    public static final String DB_USER_NAME = "root";
    public static final String DB_PASSWORD = "45673756";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/games";
    public static final String TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public static void main(String[] args) {
        GameRepository gameRepository;
        VideoGame.VideoGameBuilder videoGame = VideoGame.getBuilder();
        VideoGame game = videoGame.name("Fear").numberOfPlayers(1).genre("action").ageLimit(16).rating(8).price(300).build();
        SportGame.SportGameBuilder sportGameBuilder = SportGame.getBuilder();
        SportGame sportGame = sportGameBuilder.name("Tennis").numberOfPlayers(2).inventory(Arrays.asList("rackets", "ball")).
                sportGameType(SportGame.SportType.PERSONAL).build();
        try(Connection connection = DriverManager.getConnection(DB_URL + TIME_ZONE, DB_USER_NAME, DB_PASSWORD)){
            connection.setAutoCommit(false);
            gameRepository = new GameRepository(connection);
            //gameRepository.addGame(sportGame);
            System.out.println(gameRepository.getAll());
            //gameRepository.deleteGame("FEAR");
//            gameRepository.addGame(game);
            connection.commit();
//
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
