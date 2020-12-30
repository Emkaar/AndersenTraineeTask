package GamesCollection.commands;

import GamesCollection.utils.GameUtil;

public class ExitProgramCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Good bye");
        GameUtil.closeConnection();
        System.exit(0);
    }
}
