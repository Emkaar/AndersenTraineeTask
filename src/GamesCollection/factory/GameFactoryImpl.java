package GamesCollection.factory;

import GamesCollection.Builder.VideoGameBuilder;


public class GameFactoryImpl implements GameFactory{
    @Override
    public VideoGameBuilder createGame(String type) {
        return new VideoGameBuilder();
    }
}
