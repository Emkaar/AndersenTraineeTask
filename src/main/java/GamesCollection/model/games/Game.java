package GamesCollection.model.games;

import GamesCollection.model.collection.GameCollection;
import GamesCollection.model.users.User;
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

//    @ManyToOne
//    @JoinColumn(name = "collection_id", foreignKey = @ForeignKey(name = "fk_games_games_collection"))
//    private GameCollection collection;
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
