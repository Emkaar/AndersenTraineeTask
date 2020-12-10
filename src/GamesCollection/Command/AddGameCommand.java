package GamesCollection.Command;

import GamesCollection.Builder.VideoGameBuilder;
import GamesCollection.GameUtil;
import GamesCollection.factory.GameFactoryImpl;

import java.util.Scanner;

public class AddGameCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        GameFactoryImpl gameFactory = new GameFactoryImpl();
        VideoGameBuilder gameBuilder;
        System.out.println("Привет, это коллекция игр");
        System.out.println("Какой тип игры вы хотите добавить?");
        gameBuilder = gameFactory.createGame(scanner.nextLine());
        System.out.println("Как называется игра?");
        gameBuilder.setName(scanner.nextLine().toUpperCase());
        System.out.println("Сколько человек может в нее играть?");
        gameBuilder.setNumberOfPlayers(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Какой жанр у игры?");
        gameBuilder.setGenre(scanner.nextLine());
        System.out.println("Какой рейтинг игры по отзывам?");
        gameBuilder.setRating(Double.parseDouble(scanner.nextLine()));
        System.out.println("Какое ограничение по возрасту?");
        gameBuilder.setAgeLimit(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Какая стандартная цена игры в рублях?");
        gameBuilder.setPrice(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("Когда вышла игра? Введите день, месяц и год.");
        int day = scanner.nextInt(), month = scanner.nextInt(), year = scanner.nextInt();
        gameBuilder.setReleaseDate(year, month, day);
        GameUtil.addGameToCollection(gameBuilder.getResult());
    }
}
