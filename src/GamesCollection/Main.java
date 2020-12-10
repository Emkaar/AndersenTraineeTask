package GamesCollection;

import GamesCollection.factory.CommandFactoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите команду (ADD, DELETE, SHOW ALL, DELETE ALL, EXIT)");
        while (scanner.hasNext()){
            new CommandFactoryImpl().getCommand(scanner.nextLine().toUpperCase()).execute();
            System.out.println("Введите новую команду");
        }
    }
}
