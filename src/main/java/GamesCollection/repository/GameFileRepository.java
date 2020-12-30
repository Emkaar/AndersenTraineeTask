package GamesCollection.repository;

import GamesCollection.games.Game;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.WeakHashMap;

public class GameFileRepository implements Repository{

    private WeakHashMap<Game, Integer> games = new WeakHashMap<>();

    private File gameCollectionFile = new File("GamesCollection.ser");
    @Override
    public boolean addGame(Game game) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteGame(String name) throws SQLException {
        return false;
    }

    @Override
    public Set<Game> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean deleteAllGames() throws SQLException {
        return false;
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
}
