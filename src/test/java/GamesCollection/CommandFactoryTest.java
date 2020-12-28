package GamesCollection;

import GamesCollection.commands.AddGameCommand;
import GamesCollection.commands.Command;
import GamesCollection.commands.CommandTypes;
import GamesCollection.factory.CommandFactory;
import GamesCollection.factory.CommandFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {
        CommandFactory commandFactory = new CommandFactoryImpl();

        @Test
        public void commandFactoryCreationTest(){
            Command addCommand = commandFactory.getCommand(CommandTypes.valueOf("ADD"));
            Assert.assertTrue(addCommand instanceof AddGameCommand);
        }

        @Test(expected = IllegalArgumentException.class)
        public void commandFactoryCreationNotExistCommandTest(){
            Command notExistCommand = commandFactory.getCommand(CommandTypes.valueOf("Kuku"));
        }
}
