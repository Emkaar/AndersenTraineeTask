package GamesCollection;

import GamesCollection.Games.BoardGame;
import GamesCollection.Games.Game;
import GamesCollection.Games.SportGame;
import GamesCollection.Games.VideoGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameUtilTest {
    Game gta5 = VideoGame.getBuilder().name("gta 5").ageLimit(16).numberOfPlayers(64).genre("action").build();
    Game football = SportGame.getBuilder().name("Football").inventory(Arrays.asList("boots", "ball")).numberOfPlayers(22).build();
    Game monopoly = BoardGame.getBuilder().name("Monopoly").gameTime("3 hours").genre("strategy").numberOfPlayers(6).build();

    @Before
    public void preparation(){
        GameUtil.addGameToCollection(gta5);
        GameUtil.addGameToCollection(football);
        GameUtil.addGameToCollection(monopoly);
    }


    @Test
    public void gameUtilShowAllTest(){
        Assert.assertTrue(GameUtil.showAllGames());
    }

    @Test
    public void gameUtilDeleteAllTest(){
        GameUtil.deleteAllGames();
        Assert.assertFalse(GameUtil.deleteAllGames());
    }

    @Test
    public void gameUtilAddDuplicateTest(){
        Assert.assertFalse(GameUtil.addGameToCollection(VideoGame.getBuilder().name("gta 5").build()));
    }

    @Test
    public void gameUtilDeleteGameTest(){
        Assert.assertTrue(GameUtil.deleteGameFromCollection("Football"));
    }

    @Test
    public void gameUtilDeleteNotExistGame(){
        Assert.assertFalse(GameUtil.deleteGameFromCollection("Rome 2"));
    }
}
