package GamesCollection.model.games;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "sport_games")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SportGame extends Game {
    public enum SportGameType{COMMAND, PERSONAL, PERSONAL_COMMAND}

    private SportGameType type;
    @JsonManagedReference
    @OneToMany(mappedBy = "sportGame", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Inventory> inventoryList = new ArrayList<>();

    public SportGame(String name, int numberOfPlayers, SportGameType type, List<Inventory> inventoryList) {
        super.setName(name);
        super.setNumberOfPlayers(numberOfPlayers);
        this.type = type;
        this.inventoryList = inventoryList;
    }

    public void addInventory(Inventory inventory){
        inventoryList.add(inventory);
        inventory.setSportGame(this);
    }

    public void removeInventory(Inventory inventory){
        inventoryList.remove(inventory);
        inventory.setSportGame(null);
    }

    @Override
    public String toString() {
        return "SportGame{" +
                "name=" + super.getName() +
                ",numbers of players=" + super.getNumberOfPlayers() +
                ",type=" + type +
                ", inventoryList=" + inventoryList +
                "}";
    }
}
