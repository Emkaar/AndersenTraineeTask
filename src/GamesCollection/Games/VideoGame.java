package GamesCollection.Games;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class VideoGame extends Game{
    private String genre;
    private double rating;
    private int ageLimit; //Pan European Game Information
    private double price;
    private Calendar releaseDate = new GregorianCalendar( 1992, 0, 1);

    public VideoGame(){}

    public static class VideoGameBuilder{
        String name;
        int numberOfPlayers;
        String genre = "Без жанра";
        double rating = 0;
        int ageLimit = 0; //Pan European Game Information
        double price = 0;
        Calendar releaseDate = new GregorianCalendar( 1992, 0, 1);;

        public VideoGameBuilder() {
        }

        public VideoGameBuilder name(String name){
            this.name = name;
            return this;
        }
        public VideoGameBuilder numberOfPlayers(int numberOfPlayers){
            this.numberOfPlayers = numberOfPlayers;
            return this;
        }
        public VideoGameBuilder genre(String genre){
            this.genre = genre;
            return this;
        }

        public VideoGameBuilder rating(double rating){
            this.rating = rating;
            return this;
        }

        public VideoGameBuilder ageLimit(int ageLimit){
            this.ageLimit = ageLimit;
            return this;
        }

        public VideoGameBuilder price(double price){
            this.price = price;
            return this;
        }

        public VideoGameBuilder releaseDate(Calendar releaseDate){
            this.releaseDate = releaseDate;
            return this;
        }
        public VideoGameBuilder releaseDate(int year, int month, int day){
            this.releaseDate = new GregorianCalendar(year, month-1, day);
            return this;
        }

        public VideoGame build(){
            return new VideoGame(this);
        }
    }

    private VideoGame (VideoGameBuilder builder){
        super(builder.name, builder.numberOfPlayers);
        genre = builder.genre;
        rating = builder.rating;
        ageLimit = builder.ageLimit;
        price = builder.price;
        releaseDate = builder.releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "VideoGame{" + super.toString() +
                "genre=" + genre +
                ", rating=" + rating +
                ", ageLimit=" + ageLimit +
                ", priceRub=" + price +
                ", releaseDate=" + releaseDate.getTime() +
                "}\n";
    }
}
