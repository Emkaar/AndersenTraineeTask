package GamesCollection.commands;

import GamesCollection.utils.DBGameUtil;
import GamesCollection.utils.FileGameUtil;

import java.util.Scanner;

public class DeleteGameCommand implements Command{

    @Override
    public void execute() {
        System.out.println("Enter the name of game you want to delete.");
        String gameName = new Scanner(System.in).nextLine().toUpperCase();
        if(DBGameUtil.deleteGame(gameName)){
            System.out.println("Game " + gameName + " removed from the collection.");
        }else {
            System.out.println("A game with that name is not in the collection.");
        }

    }
}
