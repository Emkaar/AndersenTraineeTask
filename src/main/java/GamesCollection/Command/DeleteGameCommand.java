package GamesCollection.Command;

import GamesCollection.GameUtil;

import java.util.Scanner;

public class DeleteGameCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Введите название игры, которую хотите удалить.");
        String gameName = new Scanner(System.in).nextLine().toUpperCase();
        if(GameUtil.deleteGameFromCollection(gameName)){
            GameUtil.deleteGameFromCollection(gameName);
            System.out.println("Игра " + gameName + " удалена из коллекции.");
        }else {
            System.out.println("Такой игры нет в коллекции.");
        }

    }
}
