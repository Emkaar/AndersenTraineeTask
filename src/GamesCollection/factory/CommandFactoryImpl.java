package GamesCollection.factory;

import GamesCollection.Command.*;

public class CommandFactoryImpl implements CommandFactory{

    Command command = null;

    @Override
    public Command getCommand(String commandName){
        switch (commandName) {
            case "ADD":
                command = new AddGameCommand();
                break;
            case "DELETE":
                command = new DeleteGameCommand();
                break;
            case "SHOW ALL":
                command = new ShowAllGamesCommand();
                break;
            case "DELETE ALL":
                command = new DeleteAllGamesCommand();
                break;
            case "EXIT":
                command = new ExitProgramCommand();
                break;
            default:
                throw new IllegalArgumentException("Wrong command type");
        }
        return command;
    }
}
