package GamesCollection.commands;

import GamesCollection.utils.GameUtil;

public class DeleteAllGamesCommand implements Command{
    @Override
    public void execute() {
        if(GameUtil.deleteAllGames()){
            System.out.println("All games removed from the collection.");
        }else {
            System.out.println("Collection is empty, here is nothing to delete.");
        }
    }
}
