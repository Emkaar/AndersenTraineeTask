package GamesCollection.controller;

import GamesCollection.model.collection.GameCollection;
import GamesCollection.model.games.Game;
import GamesCollection.service.CollectionService;
import GamesCollection.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;
    @Autowired
    private GameDataService gameService;

    @GetMapping
    public String collectionPage(Principal principal, Model model){
        List<GameCollection> collectionList = collectionService.getCollectionsByUser(principal.getName());
        model.addAttribute("collections", collectionList);
        model.addAttribute("collection", new GameCollection());
        return "collectionList";

    }

    @PostMapping
    public String addNewCollection(Principal principal, @ModelAttribute ("collection") GameCollection collection){
        System.out.println(principal.getName());
        System.out.println(collection.getName());
        collectionService.saveCollection(principal.getName(), collection.getName());
        return "redirect:/collection";
    }

    @GetMapping("/{id}")
    public String showCollection(@PathVariable("id") Long id, Model model){
        List<Game> games = collectionService.getGamesFromCollection(id);
        model.addAttribute("games", games);
        return "allGames";
    }

    @GetMapping("/{id}/add")
    public String addNewGamePage(@PathVariable("id") Long id,
                                 @ModelAttribute("game") Game game,
                                 Model model){
        model.addAttribute("id", id);
        return "addNewGame";
    }


    @PostMapping("/add/{id}")
    public String addNewGame(@PathVariable("id") Long id,
                             @ModelAttribute("game") Game game, Model model){
        game.setCollection(collectionService.getCollectionById(id));
        gameService.addGame(game);
        return "redirect:/collection/" + id;
    }

}
