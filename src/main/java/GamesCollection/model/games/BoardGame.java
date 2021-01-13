package GamesCollection.model.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "board_games")
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame extends Game {
    private String genre;
    private double price;
    @Column(name = "game_time")
    private String gameTime;

    public BoardGame(String name, int numberOfPlayers, String genre, double price, String gameTime) {
        super.setName(name);
        super.setNumberOfPlayers(numberOfPlayers);
        this.genre = genre;
        this.price = price;
        this.gameTime = gameTime;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "name=" + super.getName() +
                ", numbers of players=" + super.getNumberOfPlayers() +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", gameTime='" + gameTime + '\'' +
                "}";
    }
}
