package GamesCollection.factory;

import GamesCollection.Builder.GameBuilder;

public interface GameFactory {
    GameBuilder createGame(String type);
}
