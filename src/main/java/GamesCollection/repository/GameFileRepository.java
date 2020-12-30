package GamesCollection.repository;

import GamesCollection.games.Game;
import lombok.NoArgsConstructor;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;


public class GameFileRepository implements Repository{

    private WeakHashMap<Game, Integer> games = new WeakHashMap<>();

    private File gameCollectionFile = new File("GamesCollection.ser");

    public GameFileRepository(){
        new GameFileRepository(gameCollectionFile);
    }

    public GameFileRepository(File gameCollectionFile) {
        this.gameCollectionFile = gameCollectionFile;
        loadGames();
    }

    @Override
    public boolean addGame(Game game) throws SQLException {
        if(games.containsKey(game)){
            return false;
        }
        if(!checkCash()){
            loadGames();
        }
        games.put(game, games.size());
        updateCollection();
        return true;
    }

    @Override
    public boolean deleteGame(String name) throws SQLException {
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

    @Override
    public Set<Game> getAll() throws SQLException {
        if(!checkCash()){
            loadGames();
        }
        if(games.isEmpty()){
            return null;
        }
        return games.keySet();
    }

    @Override
    public boolean deleteAllGames() throws SQLException {
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

    @Override
    public void exit() {

    }

    public int size(){
        return games.size();
    }

    private boolean checkCash(){
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

    private void updateCollection(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(gameCollectionFile))){
            oos.writeObject(games.size());
            oos.writeObject(new HashMap<>(games));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGames(){
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
}
