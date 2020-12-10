package GamesCollection.Builder;

import GamesCollection.Games.Game;

import java.util.Calendar;

public interface GameBuilder {
    void setName(String name);
    void setNumberOfPlayers(int numberOfPlayers);
    void setGenre(String genre);
    void setRating(double rating);
    void setAgeLimit(int ageLimit);
    void setPrice(double price);
    void setReleaseDate(int year, int month, int day);

    Game getResult();
}
