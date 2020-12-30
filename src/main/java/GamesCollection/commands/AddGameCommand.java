package GamesCollection.commands;


import GamesCollection.utils.GameUtil;
import GamesCollection.games.*;
import GamesCollection.factory.GameFactory;
import GamesCollection.factory.GameFactoryImpl;
import java.util.List;

import java.util.Arrays;
import java.util.Scanner;

public class AddGameCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        GameFactory gameFactory = new GameFactoryImpl();
        Game newGame = null;
        System.out.println("What type of game do you want to add? (video, sport or board)");
        try {
            newGame = gameFactory.createGame(GameType.valueOf(scanner.nextLine().toUpperCase()));
        } catch (IllegalArgumentException exception) {
            System.out.println("You wrote the wrong game type!");
            return;
        }

        if (newGame instanceof VideoGame) {
            VideoGame videoGame = new VideoGame();
            System.out.println("What is the name of the game?");
            videoGame.setName(scanner.nextLine().toUpperCase());
            System.out.println("What is the maximum number of players?");
            videoGame.setNumberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("What is the genre of the game?");
            videoGame.setGenre(scanner.nextLine());
            System.out.println("What is the rating of the game?");
            videoGame.setRating(Double.parseDouble(scanner.nextLine()));
            System.out.println("What is the age limit?");
            videoGame.setAgeLimit(scanner.nextInt());
            scanner.nextLine();
            System.out.println("How much does the game cost?");
            videoGame.setPrice(scanner.nextDouble());
            scanner.nextLine();
            newGame = videoGame;

        } else if (newGame instanceof SportGame) {
            SportGame sportGame = new SportGame();
            System.out.println("What is the name of the game?");
            sportGame.setName(scanner.nextLine().toUpperCase());
            System.out.println("What is the maximum number of players?");
            sportGame.setNumberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("What type is this sport game? (personal, command or personal-command)");
            sportGame.setType(SportGame.SportGameType.valueOf(scanner.nextLine().
                    toUpperCase().replace('-', '_')));
            System.out.println("What inventory is used in this game?");
            List<String> inventoryNamesList = Arrays.asList(scanner.nextLine().split(" "));
            for (String name : inventoryNamesList){
                sportGame.addInventory(new Inventory(name));
            }
            newGame = sportGame;
            ;
        } else if (newGame instanceof BoardGame) {
            BoardGame boardGame = new BoardGame();
            System.out.println("What is the name of the game?");
            boardGame.setName(scanner.nextLine().toUpperCase());
            System.out.println("What is the maximum number of players?");
            boardGame.setNumberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("What is the genre of the game?");
            boardGame.setGameTime(scanner.nextLine());
            System.out.println("How much does the game cost?");
            boardGame.setPrice(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("What is the average duration of the game?");
            boardGame.setGameTime(scanner.nextLine());
            newGame = boardGame;

        }
            if (GameUtil.addGame(newGame)) {
                System.out.println("Game " + newGame.getName() + " added in collection.");
            } else {
                System.out.println("This game already exist in collection.");
            }
    }
}

