package GamesCollection.Games;

import java.util.Calendar;

public class VideoGame extends Game{
    private String genre;
    private double rating;
    private int ageLimit; //Pan European Game Information
    private double priceRub;
    private Calendar releaseDate;

    public VideoGame() {
    }

    public VideoGame(String name, int numberOfPlayers, String genre, double rating, int ageLimit, double priceRub, Calendar releaseDate) {
        super(name, numberOfPlayers);
        this.genre = genre;
        this.rating = rating;
        this.ageLimit = ageLimit;
        this.priceRub = priceRub;
        this.releaseDate = releaseDate;
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

    public double getPriceRub() {
        return priceRub;
    }

    public void setPriceRub(double priceRub) {
        this.priceRub = priceRub;
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
                ", priceRub=" + priceRub +
                ", releaseDate=" + releaseDate.getTime() +
                "}\n";
    }
}
