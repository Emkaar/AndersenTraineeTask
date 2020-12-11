package GamesCollection.Command;


import GamesCollection.GameUtil;
import GamesCollection.Games.*;
import GamesCollection.factory.GameFactory;
import GamesCollection.factory.GameFactoryImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddGameCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        GameFactory gameFactory = new GameFactoryImpl();
        System.out.println("Какой тип игры вы хотите добавить? (video, sport or board)");
        Game newGame = gameFactory.createGame(GameType.valueOf(scanner.nextLine().toUpperCase()));
//        System.out.println("Как называется игра?");
//        newGame.setName(scanner.nextLine().toUpperCase());
//        System.out.println("Сколько человек может в нее играть?");
//        newGame.setNumberOfPlayers(scanner.nextInt());
//        scanner.nextLine();
        if(newGame instanceof VideoGame) {
            VideoGame.VideoGameBuilder gameBuilder = new VideoGame.VideoGameBuilder();
            System.out.println("Как называется игра?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("Сколько человек может в нее играть?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Какой жанр у игры?");
            gameBuilder.genre(scanner.nextLine());
            System.out.println("Какой рейтинг игры по отзывам?");
            gameBuilder.rating(Double.parseDouble(scanner.nextLine()));
            System.out.println("Какое ограничение по возрасту?");
            gameBuilder.ageLimit(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Какая стандартная цена игры в рублях?");
            gameBuilder.price(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Когда вышла игра? Введите день, месяц и год.");
            int day = scanner.nextInt(), month = scanner.nextInt(), year = scanner.nextInt();
            gameBuilder.releaseDate(year, month, day);
            newGame = gameBuilder.build();;
        }
        else if(newGame instanceof SportGame){
            SportGame.SportGameBuilder gameBuilder = new SportGame.SportGameBuilder();
            System.out.println("Как называется игра?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("Сколько человек может в нее играть?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Какой инвентарь используется?");
            gameBuilder.inventory(Arrays.asList(scanner.nextLine().split(" ")));
            newGame = gameBuilder.build();;
        }
        else if(newGame instanceof BoardGame){
            BoardGame.BoardGameBuilder gameBuilder = new BoardGame.BoardGameBuilder();
            System.out.println("Как называется игра?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("Сколько человек может в нее играть?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Какой жанр у игры?");
            gameBuilder.genre(scanner.nextLine());
            System.out.println("Какая стандартная цена игры в рублях?");
            gameBuilder.price(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Какая средняя продолжительность игры?");
            gameBuilder.gameTime(scanner.nextLine());
            newGame = gameBuilder.build();;
        }

        GameUtil.addGameToCollection(newGame);
    }
}