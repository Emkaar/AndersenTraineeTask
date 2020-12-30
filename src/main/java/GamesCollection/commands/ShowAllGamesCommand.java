package GamesCollection.commands;

import GamesCollection.utils.GameUtil;

public class ShowAllGamesCommand implements Command {
    @Override
    public void execute() {
        if (GameUtil.showAll()){
        }else {
            System.out.println("The collection is empty.");
        }
    }
}
