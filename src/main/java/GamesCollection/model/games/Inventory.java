package GamesCollection.model.games;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "fk_sport_game_inventory_sport_games"))
    private SportGame sportGame;

    public Inventory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'';
    }
}
