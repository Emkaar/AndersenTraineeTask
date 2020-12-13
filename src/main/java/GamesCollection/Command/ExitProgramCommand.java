package GamesCollection.Command;

public class ExitProgramCommand implements Command{

    @Override
    public void execute() {
        System.exit(0);
    }
}
