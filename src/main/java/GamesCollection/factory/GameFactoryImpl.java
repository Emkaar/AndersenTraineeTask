package GamesCollection.factory;


import GamesCollection.games.*;


public class GameFactoryImpl implements GameFactory{
    @Override
    public Game createGame(GameType type) throws IllegalArgumentException{
        switch (type) {
            case BOARD: return new BoardGame();
            case VIDEO: return new VideoGame();
            case SPORT: return new SportGame();
            default: throw new IllegalArgumentException();
        }
    }
}
