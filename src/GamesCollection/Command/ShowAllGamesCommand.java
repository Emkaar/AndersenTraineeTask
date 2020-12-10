package GamesCollection.Command;

import GamesCollection.GameUtil;

public class ShowAllGamesCommand implements Command {
    @Override
    public void execute() {
        GameUtil.showAllGames();
    }
}
