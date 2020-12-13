package GamesCollection.Command;

import GamesCollection.GameUtil;

public class DeleteAllGamesCommand implements Command{
    @Override
    public void execute() {
        if(GameUtil.deleteAllGames()){
            GameUtil.deleteAllGames();
            System.out.println("Все игры удалены из коллекции");
        }else {
            System.out.println("Коллекция пуста, удалять пока нечего");
        }
    }
}
