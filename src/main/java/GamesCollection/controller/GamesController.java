package GamesCollection.controller;

import GamesCollection.games.Game;
import GamesCollection.service.UserDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GamesController {
    
    UserDataService service;

    public GamesController(UserDataService service) {
        this.service = service;
    }

    @GetMapping("/games")
    public String showAllGames(Model model){
        model.addAttribute("games", service.getAll());
        return "all_games";
    }

    @GetMapping("/games/add")
    public String addNewGamePage(@ModelAttribute("game") Game game){
        return "add_new_game";
    }

    @PostMapping("/games/add")
    public String addNewGame(@ModelAttribute("game") Game game){
        service.addGame(game);
        return "redirect:/games";
    }
}
