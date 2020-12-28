package GamesCollection.Games;

public class BoardGame extends Game{
    private String genre;
    private double price;
    private String gameTime;

    public BoardGame() {
    }

    public static BoardGameBuilder getBuilder(){
        return new BoardGameBuilder();
    }

    public static class BoardGameBuilder{
        String name;
        int numberOfPlayers;
        String genre;
        double price;
        String gameTime;

        public BoardGameBuilder name(String name){
            this.name = name.toUpperCase();
            return this;
        }

        public BoardGameBuilder numberOfPlayers(int numberOfPlayers){
            this.numberOfPlayers = numberOfPlayers;
            return this;
        }

        public BoardGameBuilder genre(String genre){
            this.genre = genre;
            return this;
        }

        public BoardGameBuilder price(double price){
            this.price = price;
            return this;
        }

        public BoardGameBuilder gameTime(String gameTime){
            this.gameTime = gameTime;
            return this;
        }

        public BoardGame build(){
            return new BoardGame(this);
        }
    }

    private BoardGame(BoardGameBuilder builder){
        super(builder.name, builder.numberOfPlayers);
        genre = builder.genre;
        price = builder.price;
        gameTime = builder.gameTime;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public String getGameTime() {
        return gameTime;
    }

    @Override
    public String toString() {
        return "BoardGame{" + super.toString() +
                "genre='" + genre + '\'' +
                ", price=" + price +
                ", gameTime='" + gameTime + '\'' +
                "} \n";
    }
}
