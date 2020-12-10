package GamesCollection.factory;

import GamesCollection.Command.Command;

public interface CommandFactory {
    Command getCommand(String command);
}
