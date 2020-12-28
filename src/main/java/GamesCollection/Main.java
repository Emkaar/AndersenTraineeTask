package GamesCollection;

import GamesCollection.commands.CommandTypes;
import GamesCollection.factory.CommandFactoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //FileGameUtil.loadGames();
        System.out.println("Enter the command (ADD, DELETE, SHOW ALL, DELETE ALL, EXIT)");
        while (scanner.hasNext()){
            CommandTypes command = null;
            try {
                command = CommandTypes.valueOf(scanner.nextLine().replace(' ', '_').toUpperCase());
                new CommandFactoryImpl().getCommand(command).execute();
            }catch (IllegalArgumentException exception){
                System.out.println("You have entered the wrong command.");
            }
            System.out.println("Enter the command (ADD, DELETE, SHOW ALL, DELETE ALL, EXIT)");
        }
    }
}
