package GamesCollection.Builder;

import GamesCollection.Games.VideoGame;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class VideoGameBuilder implements GameBuilder{
    private String name;
    private int numberOfPlayers;
    private String genre;
    private double rating;
    private int ageLimit;
    private double price;
    private Calendar releaseDate;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setReleaseDate(int year, int month, int day){
        this.releaseDate = new GregorianCalendar(year, month-1, day);
    }
    @Override
    public VideoGame getResult(){
        return new VideoGame(name, numberOfPlayers, genre, rating, ageLimit, price, releaseDate);
    }
}
