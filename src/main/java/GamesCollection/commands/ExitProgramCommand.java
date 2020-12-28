package GamesCollection.commands;

import GamesCollection.utils.FileGameUtil;

public class ExitProgramCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Good bye");
        System.exit(0);
    }
}
