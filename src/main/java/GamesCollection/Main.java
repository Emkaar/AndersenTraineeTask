package GamesCollection;

import GamesCollection.Command.CommandTypes;
import GamesCollection.factory.CommandFactoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду (ADD, DELETE, SHOW ALL, DELETE ALL, EXIT)");
        while (scanner.hasNext()){
            CommandTypes command = CommandTypes.valueOf(scanner.nextLine().replace(' ', '_').toUpperCase());
            new CommandFactoryImpl().getCommand(command).execute();
            System.out.println("Введите новую команду");
        }
    }
}
