package GamesCollection.Games;

import java.util.ArrayList;
import java.util.List;

public class SportGame extends Game{
    public enum SportType{PERSONAL, COMMAND, PERSONAL_COMMAND}
    private SportType sportType;
    private List<String> inventory = new ArrayList<>();

    public SportGame(){}

    public static SportGameBuilder getBuilder(){
        return new SportGameBuilder();
    }

    public static class SportGameBuilder{
        String name;
        int numberOfPlayers;
        SportType sportType;
        private List<String> inventory = new ArrayList<>();

        public SportGameBuilder() {
        }

        public SportGameBuilder name(String name){
            this.name = name.toUpperCase();
            return this;
        }
        public SportGameBuilder numberOfPlayers(int numberOfPlayers){
            this.numberOfPlayers = numberOfPlayers;
            return this;
        }


        public SportGameBuilder sportGameType(SportType sportType){
            this.sportType = sportType;
            return this;
        }

        public SportGameBuilder inventory(List<String> inventory){
            this.inventory = inventory;
            return this;
        }

        public SportGame build(){
            return new SportGame(this);
        }
    }

    private SportGame(SportGameBuilder builder){
        super(builder.name, builder.numberOfPlayers);
        sportType = builder.sportType;
        inventory = builder.inventory;
    }

    @Override
    public String toString() {
        return "SportGame{" + super.toString() +
                "sport type=" + sportType.name() + ", " +
                "inventory=" + inventory +
                "} \n";
    }
}
