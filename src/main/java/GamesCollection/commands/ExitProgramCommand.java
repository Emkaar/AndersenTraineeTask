package GamesCollection.commands;

import GamesCollection.utils.DBGameUtil;
import GamesCollection.utils.FileGameUtil;

public class ExitProgramCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Good bye");
        DBGameUtil.closeConnection();
        System.exit(0);
    }
}
