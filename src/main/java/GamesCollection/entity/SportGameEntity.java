package GamesCollection.entity;

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
public class SportGameEntity extends GameEntity {
    public enum SportGameType{COMMAND, PERSONAL, PERSONAL_COMMAND}

    private SportGameType type;
//    @OneToMany(mappedBy = "sportGame", cascade = CascadeType.ALL)
//    private List<Inventory> inventoryList;

}
