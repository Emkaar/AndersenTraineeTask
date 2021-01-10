package GamesCollection.games;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Table(name = "sport_games")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SportGame extends Game {
    public enum SportGameType{COMMAND, PERSONAL, PERSONAL_COMMAND}

    private SportGameType type;
    @OneToMany(mappedBy = "sportGame", cascade = CascadeType.ALL)
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
