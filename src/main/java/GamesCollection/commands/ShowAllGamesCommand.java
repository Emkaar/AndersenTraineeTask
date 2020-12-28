package GamesCollection.commands;

import GamesCollection.utils.DBGameUtil;
import GamesCollection.utils.FileGameUtil;

public class ShowAllGamesCommand implements Command {
    @Override
    public void execute() {
        if (DBGameUtil.showAll()){
        }else {
            System.out.println("The collection is empty.");
        }
    }
}
