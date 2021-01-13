package GamesCollection.controller;

import GamesCollection.model.games.Game;
import GamesCollection.service.GameDataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class GamesControllerTest {

    MockMvc mockMvc;

    @Mock
    GameDataService gameService;

    @InjectMocks
    GamesController gamesController = new GamesController();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gamesController).build();
    }

    @Test
    public void showAllGamesTest() throws Exception {
        List<Game>games = Arrays.asList(new Game(), new Game());

        when(gameService.getAll()).thenReturn(games);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(view().name("allGames"))
                .andExpect(model().attributeExists("games"))
                .andExpect(header().doesNotExist("superHeader"))
                .andExpect(model().attribute("games", games));
    }

    @Test
    public void addNewGameTest() throws Exception {
        mockMvc.perform(post("/games/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("game"))
                .andExpect(redirectedUrl("/games"));
    }

    @Test
    public void jsonGetTest() throws Exception {
        Game game = new Game();
        game.setName("TOTAL WAR");

        when(gameService.getById(any())).thenReturn(game);

        mockMvc.perform(get("/game/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("TOTAL WAR"));
    }
}
