package GamesCollection.entity;

import GamesCollection.games.SportGame;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sport_game_inventory")
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private SportGameEntity sportGame;

    public Inventory(String name) {
        this.name = name;
    }
}
