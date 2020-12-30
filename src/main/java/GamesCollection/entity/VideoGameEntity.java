package GamesCollection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "video_games")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VideoGameEntity extends GameEntity{
    private String genre;
    private double rating;
    @Column(name = "age_limit")
    private int ageLimit;
    private double price;
}
