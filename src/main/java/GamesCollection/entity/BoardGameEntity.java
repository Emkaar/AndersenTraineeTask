package GamesCollection.entity;

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
public class BoardGameEntity extends GameEntity{
    private String genre;
    private double price;
    @Column(name = "game_time")
    private String gameTime;
}
