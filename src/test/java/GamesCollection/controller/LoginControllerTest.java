package GamesCollection.controller;

import GamesCollection.config.MvcConfig;
import GamesCollection.config.SpringConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringConfig.class)
public class LoginControllerTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .param("username", "pepe")
            .param("password", "pepe"))
                .andDo(MockMvcResultHandlers.print());
    }
}
