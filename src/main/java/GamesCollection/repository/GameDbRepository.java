package GamesCollection.repository;

import GamesCollection.connection.DbConnector;
import GamesCollection.games.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameDbRepository implements Repository {
    private Connection connection;

    public GameDbRepository(){
        new GameDbRepository(DbConnector.getConnection());
    }

    public GameDbRepository(Connection connection) {
        this.connection = connection;
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public boolean addGame(Game game) throws SQLException {
        if (isGameExist(game.getName())){
            return false;
        }else {
            PreparedStatement statement = connection.
                    prepareStatement("insert into games.games (name, number_of_players) values (?, ?);");
            statement.setString(1, game.getName());
            statement.setInt(2, game.getNumberOfPlayers());
            statement.execute();
            PreparedStatement id = connection.prepareStatement("select id from games.games where name = ?;");
            id.setString(1, game.getName());
            ResultSet newGameId = id.executeQuery();
            int gameId = 0;
            while (newGameId.next()) {
                gameId = newGameId.getInt("id");
            }

            if (game instanceof VideoGame) {
                addVideoGame(game, gameId);
            } else if (game instanceof SportGame) {
                addSportGame(game, gameId);
            } else if(game instanceof BoardGame){
                addBoardGame(game, gameId);
            }
            connection.commit();
            return true;
        }
    }

    private void addBoardGame(Game game, int gameId) throws SQLException {
        BoardGame boardGame = (BoardGame) game;
        PreparedStatement statement = connection.prepareStatement("update games.games set type='board' where id = ?");
        statement.setInt(1, gameId);
        statement.executeUpdate();
        PreparedStatement boardGameStatement = connection.prepareStatement("insert into games.board_games values (?, ?, ?, ?)");
        boardGameStatement.setInt(1, gameId);
        boardGameStatement.setString(2, boardGame.getGenre());
        boardGameStatement.setDouble(3, boardGame.getPrice());
        boardGameStatement.setString(4, boardGame.getGameTime());
        boardGameStatement.execute();
    }

    private void addSportGame(Game game, int gameId) throws SQLException {
        SportGame sportGame = (SportGame)game;
        PreparedStatement statement = connection.prepareStatement("update games.games set type='sport' where id = ?");
        statement.setInt(1, gameId);
        statement.executeUpdate();
        PreparedStatement sportGameStatement = connection.prepareStatement("insert into games.sport_games values (?, ?)");
        sportGameStatement.setInt(1, gameId);
        sportGameStatement.setInt(2, sportGame.getType().ordinal());
        sportGameStatement.execute();
        for (Inventory item : sportGame.getInventoryList()){
            PreparedStatement inventory = connection.prepareStatement("insert into sport_game_inventory (game_id, name) values (?, ?)");
            inventory.setInt(1, gameId);
            inventory.setString(2, item.getName());
            inventory.execute();
        }
    }

    private void addVideoGame(Game game, int gameId) throws SQLException {
        VideoGame videoGame = (VideoGame)game;
        PreparedStatement statement = connection.prepareStatement("update games.games set type='video' where id = ?");
        statement.setInt(1, gameId);
        statement.executeUpdate();
        PreparedStatement videoGameStatement = connection.prepareStatement("insert into games.video_games values (?, ?, ?, ?, ?);");
        videoGameStatement.setInt(1, gameId);
        videoGameStatement.setString(2, videoGame.getGenre());
        videoGameStatement.setDouble(3, videoGame.getRating());
        videoGameStatement.setInt(4, videoGame.getAgeLimit());
        videoGameStatement.setDouble(5, videoGame.getPrice());
        videoGameStatement.execute();
    }

    public boolean deleteGame(String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from games.games where name = ?");
        statement.setString(1, name);
        int updatedRows = statement.executeUpdate();
        connection.commit();
        return updatedRows != 0;
    }

    public Set<Game> getAll() throws SQLException {
        Set<Game> resultGameSet = new HashSet<>();
        PreparedStatement videoGameStatement = connection.prepareStatement(
                "select name, number_of_players, genre, rating, age_limit, price " +
                "from games inner join video_games on games.id = video_games.id " +
                "group by name");
        videoGameStatement.execute();
        ResultSet videoGamesResultSet = videoGameStatement.getResultSet();
        while (videoGamesResultSet.next()){
            VideoGame game = new VideoGame();
            game.setName(videoGamesResultSet.getString(1));
            game.setNumberOfPlayers(videoGamesResultSet.getInt(2));
            game.setGenre(videoGamesResultSet.getString(3));
            game.setRating(videoGamesResultSet.getDouble(4));
            game.setAgeLimit(videoGamesResultSet.getInt(5));
            game.setPrice(videoGamesResultSet.getDouble(6));
            resultGameSet.add(game);
        }
        Statement boardGamesStatement = connection.createStatement();
        boardGamesStatement.execute(
                "select name, number_of_players, genre, price, game_time " +
                "from games inner join board_games on games.id = board_games.id " +
                "group by name");
        ResultSet boardGamesResultSet = boardGamesStatement.getResultSet();
        while (boardGamesResultSet.next()){
            BoardGame game = new BoardGame();
            game.setName(boardGamesResultSet.getString(1));
            game.setNumberOfPlayers(boardGamesResultSet.getInt(2));
            game.setGenre(boardGamesResultSet.getString(3));
            game.setPrice(boardGamesResultSet.getDouble(4));
            game.setGameTime(boardGamesResultSet.getString(5));
            resultGameSet.add(game);
        }

        Statement sportGameStatement = connection.createStatement();
        sportGameStatement.execute(
                "select games.id, name, number_of_players, sport_games.type " + "" +
                    "from games inner join sport_games " +
                    "on games.id=sport_games.id"
        );
        ResultSet sportGamesResultSet = sportGameStatement.getResultSet();
        while (sportGamesResultSet.next()){
            ArrayList<Inventory>inventory = new ArrayList<>();
            PreparedStatement inventoryStatement = connection.prepareStatement(
                    "select sport_game_inventory.name " +
                        "from sport_game_inventory " +
                        "where game_id=?"
            );
            inventoryStatement.setInt(1,sportGamesResultSet.getInt(1));
            inventoryStatement.execute();
            ResultSet inventoryResultSet = inventoryStatement.getResultSet();
            while (inventoryResultSet.next()){
                inventory.add(new Inventory(inventoryResultSet.getString(1)));
            }
            SportGame game = new SportGame();
            game.setName(sportGamesResultSet.getString(2));
            game.setNumberOfPlayers(sportGamesResultSet.getInt(3));
            game.setType(SportGame.SportGameType.valueOf(sportGamesResultSet.getString(4)));
            game.setInventoryList(inventory);
            resultGameSet.add(game);
        }
        return resultGameSet;
    }

    @Override
    public boolean deleteAllGames(){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from games");
            int updatedRows = statement.executeUpdate();
            connection.commit();
            return updatedRows != 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public void exit() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private boolean isGameExist(String name){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("select * from games where name=?");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            return preparedStatement.getResultSet().next();
        } catch (SQLException exception) {
            System.out.println("Something wrong in check");
            return false;
        }
    }
}
