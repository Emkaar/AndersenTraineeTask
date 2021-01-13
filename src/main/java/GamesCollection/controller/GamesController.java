package GamesCollection.controller;

import GamesCollection.model.games.Game;
import GamesCollection.model.users.User;
import GamesCollection.service.GameDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class GamesController {
    @Autowired
    GameDataService service;


//    @GetMapping("/games")
//    public String showAllGames(Authentication authentication, Model model){
//        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
//        User user = (User)userDetails;
//        model.addAttribute("games", service.getAll());
//        return "allGames";
//    }





    @GetMapping("/game/{id}")
    public @ResponseBody Game getJsonById(@PathVariable(value = "id") Integer id){
        Game game = service.getById(id);
        return game != null ? game : new Game();
    }

}
