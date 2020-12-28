package GamesCollection.commands;


import GamesCollection.utils.DBGameUtil;
import GamesCollection.utils.FileGameUtil;
import GamesCollection.games.*;
import GamesCollection.factory.GameFactory;
import GamesCollection.factory.GameFactoryImpl;

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
            VideoGame.VideoGameBuilder gameBuilder = VideoGame.getBuilder();
            System.out.println("What is the name of the game?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("What is the maximum number of players?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("What is the genre of the game?");
            gameBuilder.genre(scanner.nextLine());
            System.out.println("What is the rating of the game?");
            gameBuilder.rating(Double.parseDouble(scanner.nextLine()));
            System.out.println("What is the age limit?");
            gameBuilder.ageLimit(scanner.nextInt());
            scanner.nextLine();
            System.out.println("How much does the game cost?");
            gameBuilder.price(scanner.nextDouble());
            scanner.nextLine();
            newGame = gameBuilder.build();

        } else if (newGame instanceof SportGame) {
            SportGame.SportGameBuilder gameBuilder = new SportGame.SportGameBuilder();
            System.out.println("What is the name of the game?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("What is the maximum number of players?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("What type is this sport game? (personal, command or personal-command)");
            gameBuilder.sportGameType(SportGame.SportType.valueOf(scanner.nextLine().
                    toUpperCase().replace('-', '_')));
            System.out.println("What inventory is used in this game?");
            gameBuilder.inventory(Arrays.asList(scanner.nextLine().split(" ")));
            newGame = gameBuilder.build();
            ;
        } else if (newGame instanceof BoardGame) {
            BoardGame.BoardGameBuilder gameBuilder = new BoardGame.BoardGameBuilder();
            System.out.println("What is the name of the game?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("What is the maximum number of players?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("What is the genre of the game?");
            gameBuilder.genre(scanner.nextLine());
            System.out.println("How much does the game cost?");
            gameBuilder.price(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("What is the average duration of the game?");
            gameBuilder.gameTime(scanner.nextLine());
            newGame = gameBuilder.build();

        }
            if (DBGameUtil.addGame(newGame)) {
                System.out.println("Game " + newGame.getName() + " added in collection.");
            } else {
                System.out.println("This game already exist in collection.");
            }
    }
}

