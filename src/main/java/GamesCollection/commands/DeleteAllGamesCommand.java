package GamesCollection.commands;

import GamesCollection.utils.DBGameUtil;
import GamesCollection.utils.FileGameUtil;

public class DeleteAllGamesCommand implements Command{
    @Override
    public void execute() {
        if(DBGameUtil.deleteAllGames()){
            System.out.println("All games removed from the collection.");
        }else {
            System.out.println("Collection is empty, here is nothing to delete.");
        }
    }
}
