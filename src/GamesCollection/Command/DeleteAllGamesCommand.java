package GamesCollection.Command;

import GamesCollection.GameUtil;

public class DeleteAllGamesCommand implements Command{
    @Override
    public void execute() {
        GameUtil.deleteAllGames();
    }
}
