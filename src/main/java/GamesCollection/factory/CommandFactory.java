package GamesCollection.factory;

import GamesCollection.Command.Command;
import GamesCollection.Command.CommandTypes;

public interface CommandFactory {
    Command getCommand(CommandTypes command);
}
