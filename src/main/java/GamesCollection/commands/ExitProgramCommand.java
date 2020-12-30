package GamesCollection.commands;

import GamesCollection.utils.GameUtil;

public class ExitProgramCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Good bye");
        GameUtil.exit();
        System.exit(0);
    }
}
