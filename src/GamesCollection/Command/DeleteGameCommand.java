package GamesCollection.Command;

import GamesCollection.GameUtil;

import java.util.Scanner;

public class DeleteGameCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Введите название игры, которую хотите удалить.");
        GameUtil.deleteGameFromCollection(new Scanner(System.in).nextLine().toUpperCase());
    }
}
