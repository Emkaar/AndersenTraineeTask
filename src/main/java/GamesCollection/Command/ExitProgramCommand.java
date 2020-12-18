package GamesCollection.Command;

import GamesCollection.GameUtil;

public class ExitProgramCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Collection have " + GameUtil.size() + " games.");
        System.exit(0);
    }
}
