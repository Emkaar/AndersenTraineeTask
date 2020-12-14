package GamesCollection.Command;


import GamesCollection.GameUtil;
import GamesCollection.Games.*;
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
        System.out.println("Какой тип игры вы хотите добавить? (video, sport or board)");
        try {
            newGame = gameFactory.createGame(GameType.valueOf(scanner.nextLine().toUpperCase()));
        } catch (IllegalArgumentException exception) {
            System.out.println("Вы написали неверный тип игры.");
            return;
        }

        if (newGame instanceof VideoGame) {
            VideoGame.VideoGameBuilder gameBuilder = VideoGame.getBuilder();
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
            newGame = gameBuilder.build();
            ;
        } else if (newGame instanceof SportGame) {
            SportGame.SportGameBuilder gameBuilder = new SportGame.SportGameBuilder();
            System.out.println("Как называется игра?");
            gameBuilder.name(scanner.nextLine().toUpperCase());
            System.out.println("Сколько человек может в нее играть?");
            gameBuilder.numberOfPlayers(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Какой инвентарь используется?");
            gameBuilder.inventory(Arrays.asList(scanner.nextLine().split(" ")));
            newGame = gameBuilder.build();
            ;
        } else if (newGame instanceof BoardGame) {
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
            newGame = gameBuilder.build();


            if (GameUtil.addGameToCollection(newGame)) {
                GameUtil.addGameToCollection(newGame);
                System.out.println("Игра " + newGame.getName() + " добавлена в коллекцию.");
            } else {
                System.out.println("Такая игра уже есть в коллекции.");
            }
        }
    }
}
