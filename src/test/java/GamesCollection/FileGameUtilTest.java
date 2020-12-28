package GamesCollection;

import GamesCollection.games.BoardGame;
import GamesCollection.games.Game;
import GamesCollection.games.SportGame;
import GamesCollection.games.VideoGame;
import GamesCollection.utils.FileGameUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class FileGameUtilTest {
    Game gta5 = VideoGame.getBuilder().name("gta 5").ageLimit(16).numberOfPlayers(64).genre("action").build();
    Game football = SportGame.getBuilder().name("Football").inventory(Arrays.asList("boots", "ball")).numberOfPlayers(22).build();
    Game monopoly = BoardGame.getBuilder().name("Monopoly").gameTime("3 hours").genre("strategy").numberOfPlayers(6).build();

    @Before
    public void preparation(){
        FileGameUtil.addGameToCollection(gta5);
        FileGameUtil.addGameToCollection(football);
        FileGameUtil.addGameToCollection(monopoly);
    }


    @Test
    public void gameUtilShowAllTest(){
        Assert.assertTrue(FileGameUtil.showAllGames());
    }

    @Test
    public void gameUtilDeleteAllTest(){
        FileGameUtil.deleteAllGames();
        Assert.assertFalse(FileGameUtil.deleteAllGames());
    }

    @Test
    public void gameUtilAddDuplicateTest(){
        Assert.assertFalse(FileGameUtil.addGameToCollection(VideoGame.getBuilder().name("gta 5").build()));
    }

    @Test
    public void gameUtilDeleteGameTest(){
        Assert.assertTrue(FileGameUtil.deleteGameFromCollection("Football"));
    }

    @Test
    public void gameUtilDeleteNotExistGame(){
        Assert.assertFalse(FileGameUtil.deleteGameFromCollection("Rome 2"));
    }
}
