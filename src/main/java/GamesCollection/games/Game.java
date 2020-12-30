package GamesCollection.games;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    @Column(name = "number_of_players")
    private int numberOfPlayers;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return name.equals(game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
