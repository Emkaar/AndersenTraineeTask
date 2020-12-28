package GamesCollection.factory;

import GamesCollection.games.Game;
import GamesCollection.games.GameType;

public interface GameFactory {
    Game createGame(GameType type);
}
