package GamesCollection.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@Entity
@Table(name = "video_games")
@NoArgsConstructor
@AllArgsConstructor
public class VideoGame extends Game {
    private String genre;
    private double rating;
    @Column(name = "age_limit")
    private int ageLimit; //Pan European Game Information
    private double price;

    public static VideoGameBuilder getBuilder(){
        return new VideoGameBuilder();
    }

    public static class VideoGameBuilder{
        String name;
        int numberOfPlayers;
        String genre = "Без жанра";
        double rating = 0;
        int ageLimit = 0; //Pan European Game Information
        double price = 0;
        Calendar releaseDate = new GregorianCalendar( 1992, 0, 1);

        public VideoGameBuilder() {
        }

        public VideoGameBuilder name(String name){
            this.name = name.toUpperCase();
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

        public VideoGame build(){
            return new VideoGame(this);
        }
    }

    private VideoGame(VideoGameBuilder builder){
        super(builder.name, builder.numberOfPlayers);
        genre = builder.genre;
        rating = builder.rating;
        ageLimit = builder.ageLimit;
        price = builder.price;
    }

    @Override
    public String toString() {
        return "VideoGame{" + super.toString() +
                "genre=" + genre +
                ", rating=" + rating +
                ", ageLimit=" + ageLimit +
                ", priceRub=" + price +
                "}\n";
    }
}
