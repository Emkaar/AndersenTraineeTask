package GamesCollection.factory;

import GamesCollection.commands.Command;
import GamesCollection.commands.CommandTypes;

public interface CommandFactory {
    Command getCommand(CommandTypes command);
}
