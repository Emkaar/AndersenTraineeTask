package GamesCollection;

import GamesCollection.Games.VideoGame;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameUtil {
    private static Set<VideoGame> videoGames = new HashSet<>();

    public static void addGameToCollection(VideoGame newGame){
        videoGames.add(newGame);
        System.out.println("Игра " + newGame.getName() + " добавлена в коллекцию.");
    }
    public static void deleteGameFromCollection(String name){
        boolean collectionHaveThatGame = videoGames.stream().anyMatch(g -> g.getName().equals(name));
        if(!collectionHaveThatGame){
            System.out.println("Такой игры нет в коллекции.");
        }
        else {
            Iterator<VideoGame> gameIterator = videoGames.iterator();
            while (gameIterator.hasNext()) {
                String gameName = gameIterator.next().getName();
                if (gameName.equals(name)) {
                    gameIterator.remove();
                    System.out.println("Игра " + name + " удалена из коллекции");
                }
            }
        }
    }

    public static void showAllGames(){
        System.out.println(videoGames);
    }

    public static void deleteAllGames(){
        if(videoGames.isEmpty()){
            System.out.println("Коллекция пуста, удалять пока нечего");
        }
        else {
            videoGames.clear();
            System.out.println("Все игры удалены из коллекции");
        }
    }

}
