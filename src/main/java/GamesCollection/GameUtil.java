package GamesCollection;

import GamesCollection.Games.Game;

import java.io.*;
import java.util.*;

public class GameUtil {
    private static WeakHashMap<Game, Integer> games = new WeakHashMap<>();

    private static File gameCollectionFile = new File("GamesCollection.ser");

    public static void loadGames(){
        if(!gameCollectionFile.exists()){
            updateCollection();
            System.out.println("New collection created!");
        }else {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(gameCollectionFile))) {
                int loadedSize = (Integer) is.readObject();
                games = new WeakHashMap<>((HashMap<Game, Integer>) is.readObject());
                System.out.println("Your collection consist of " + loadedSize + " games.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean addGameToCollection(Game newGame){
        if(games.containsKey(newGame)){
            return false;
        }
        if(!checkCash()){
            loadGames();
        }
        games.put(newGame, games.size());
        updateCollection();
        return true;
    }
    public static boolean deleteGameFromCollection(String name) {
        if(!checkCash()){
            loadGames();
        }
        Iterator<Game> gameIterator = games.keySet().iterator();
        while (gameIterator.hasNext()) {
            String gameName = gameIterator.next().getName();
            if (gameName.equals(name.toUpperCase())) {
                gameIterator.remove();
                updateCollection();
                return true;
            }
        }
        return false;
    }

    public static boolean showAllGames(){
        if(!checkCash()){
            loadGames();
        }
        if(games.isEmpty()){
            return false;
        }
        System.out.println(games.keySet());
        return true;
    }


    public static boolean deleteAllGames(){
        if(!checkCash()){
            loadGames();
        }
        if(games.isEmpty()){
            return false;
        }
        games.clear();
        updateCollection();
        return true;
    }

    public static int size(){
        return games.size();
    }

    private static boolean checkCash(){
        int fileCollectionSize = 0;
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream(gameCollectionFile))) {
            fileCollectionSize = (Integer)oos.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return games.size() == fileCollectionSize;
    }



    private static void updateCollection(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(gameCollectionFile))){
            oos.writeObject(games.size());
            oos.writeObject(new HashMap<>(games));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
