package GamesCollection.factory;

import GamesCollection.Games.Game;
import GamesCollection.Games.GameType;

public interface GameFactory {
    Game createGame(GameType type);
}
