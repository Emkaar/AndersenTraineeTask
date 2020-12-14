package GamesCollection.Command;

import GamesCollection.GameUtil;

public class ShowAllGamesCommand implements Command {
    @Override
    public void execute() {
        if (GameUtil.showAllGames()){
        }else {
            System.out.println("В коллекции еще нет игр.");
        }
    }
}
