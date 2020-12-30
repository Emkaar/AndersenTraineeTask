package GamesCollection.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "games")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    @Column(name = "number_of_players")
    private int numberOfPlayers;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return numberOfPlayers == game.numberOfPlayers &&
                name.equals(game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfPlayers);
    }
}
