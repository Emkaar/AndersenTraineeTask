package GamesCollection.model.collection;

import GamesCollection.model.games.Game;
import GamesCollection.model.games.SportGame;
import GamesCollection.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_collection")
public class GameCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "collection", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Game> gameList = new ArrayList<>();
}
