package GamesCollection;

import GamesCollection.Games.Game;
import GamesCollection.Games.GameType;
import GamesCollection.Games.SportGame;
import GamesCollection.factory.GameFactory;
import GamesCollection.factory.GameFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

public class GameFactoryTest {
    GameFactory gameFactory = new GameFactoryImpl();

    @Test
    public void gameFactoryCreationTest(){
        Game sportGame = gameFactory.createGame(GameType.valueOf("SPORT"));
        Assert.assertTrue(sportGame instanceof SportGame);
    }

    @Test(expected = IllegalArgumentException.class)
    public void gameFactoryCreationNotExistTypeGameTest(){
        Game notExistGame = gameFactory.createGame(GameType.valueOf("fight"));
    }
}
