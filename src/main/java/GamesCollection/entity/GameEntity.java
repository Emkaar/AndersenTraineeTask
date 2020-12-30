package GamesCollection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "games")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(name = "number_of_players")
    private int numberOfPlayers;
}
