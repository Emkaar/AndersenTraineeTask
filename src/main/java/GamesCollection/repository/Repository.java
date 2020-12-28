package GamesCollection.repository;

import GamesCollection.games.Game;

import java.sql.SQLException;
import java.util.Set;

public interface Repository {
        public boolean addGame(Game game) throws SQLException;
        public boolean deleteGame(String name) throws SQLException;
        public Set<Game> getAll() throws SQLException;
        public boolean deleteAllGames() throws SQLException;
}
