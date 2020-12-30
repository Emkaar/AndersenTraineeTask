package GamesCollection.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "video_games")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VideoGame extends Game {
    private String genre;
    private double rating;
    @Column(name = "age_limit")
    private int ageLimit;
    private double price;

    @Override
    public String toString() {
        return "VideoGame{" +
                "name=" + super.getName() +
                ", numbers of players=" + super.getNumberOfPlayers() +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                "}";
    }
}
