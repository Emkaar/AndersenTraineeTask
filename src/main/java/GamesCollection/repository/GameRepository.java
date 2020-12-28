package GamesCollection.repository;

import GamesCollection.games.BoardGame;
import GamesCollection.games.Game;
import GamesCollection.games.SportGame;
import GamesCollection.games.VideoGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameRepository implements Repository {
    private Connection connection;

    public GameRepository(Connection connection) {
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
        sportGameStatement.setString(2, sportGame.getSportType().name());
        sportGameStatement.execute();
        for (String item : sportGame.getInventory()){
            PreparedStatement inventory = connection.prepareStatement("insert into sport_game_inventory (game_id, name) values (?, ?)");
            inventory.setInt(1, gameId);
            inventory.setString(2, item);
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
        if(updatedRows==0){
            return false;
        }else {
            return true;
        }
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
            resultGameSet.add(VideoGame.getBuilder()
                    .name(videoGamesResultSet.getString(1))
                    .numberOfPlayers(videoGamesResultSet.getInt(2))
                    .genre(videoGamesResultSet.getString(3))
                    .rating(videoGamesResultSet.getDouble(4))
                    .ageLimit(videoGamesResultSet.getInt(5))
                    .price(videoGamesResultSet.getDouble(6))
                    .build());
        }
        Statement boardGamesStatement = connection.createStatement();
        boardGamesStatement.execute(
                "select name, number_of_players, genre, price, game_time " +
                "from games inner join board_games on games.id = board_games.id " +
                "group by name");
        ResultSet boardGamesResultSet = boardGamesStatement.getResultSet();
        while (boardGamesResultSet.next()){
            resultGameSet.add(BoardGame.getBuilder()
                    .name(boardGamesResultSet.getString(1))
                    .numberOfPlayers(boardGamesResultSet.getInt(2))
                    .genre(boardGamesResultSet.getString(3))
                    .price(boardGamesResultSet.getDouble(4))
                    .gameTime(boardGamesResultSet.getString(5))
                    .build());
        }

        Statement sportGameStatement = connection.createStatement();
        sportGameStatement.execute(
                "select games.id, name, number_of_players, sport_games.type " + "" +
                    "from games inner join sport_games " +
                    "on games.id=sport_games.id"
        );
        ResultSet sportGamesResultSet = sportGameStatement.getResultSet();
        while (sportGamesResultSet.next()){
            ArrayList<String>inventory = new ArrayList<>();
            PreparedStatement inventoryStatement = connection.prepareStatement(
                    "select sport_game_inventory.name " +
                        "from sport_game_inventory " +
                        "where game_id=?"
            );
            inventoryStatement.setInt(1,sportGamesResultSet.getInt(1));
            inventoryStatement.execute();
            ResultSet inventoryResultSet = inventoryStatement.getResultSet();
            while (inventoryResultSet.next()){
                inventory.add(inventoryResultSet.getString(1));
            }
            resultGameSet.add(SportGame.getBuilder()
                    .name(sportGamesResultSet.getString(2))
                    .numberOfPlayers(sportGamesResultSet.getInt(3))
                    .sportGameType(SportGame.SportType.valueOf(sportGamesResultSet.getString(4)))
                    .inventory(inventory)
                    .build());
        }
        return resultGameSet;
    }

    @Override
    public boolean deleteAllGames(){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from games");
            int updatedRows = statement.executeUpdate();
            connection.commit();
            if(updatedRows==0){
                return false;
            }
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private boolean isGameExist(String name){
        PreparedStatement preparedStatement = null;
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
