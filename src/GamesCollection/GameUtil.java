package GamesCollection;

import GamesCollection.Games.Game;
import GamesCollection.Games.VideoGame;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameUtil {
    private static Set<Game> videoGames = new HashSet<>();

    public static boolean addGameToCollection(Game newGame){
        return videoGames.add(newGame);
    }
    public static boolean deleteGameFromCollection(String name) {
        Iterator<Game> gameIterator = videoGames.iterator();
        while (gameIterator.hasNext()) {
            String gameName = gameIterator.next().getName();
            if (gameName.equals(name)) {
                gameIterator.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean showAllGames(){
        if(videoGames.isEmpty()){
            return false;
        }else {
            System.out.println(videoGames);
            return true;
        }
    }


    public static boolean deleteAllGames(){
        if(videoGames.isEmpty()){
            return false;
        }
        else {
            videoGames.clear();
            return true;
        }
    }

}
