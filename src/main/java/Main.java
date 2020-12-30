import GamesCollection.games.Inventory;
import GamesCollection.games.SportGame;
import GamesCollection.repository.GameHibernateRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        GameHibernateRepository gameHibernateRepository = new GameHibernateRepository();

        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            SportGame sportGame = new SportGame();
//            sportGame.setName("BasketBall");
//            sportGame.setNumberOfPlayers(12);
//            sportGame.setType(SportGame.SportGameType.COMMAND);
//            sportGame.addInventory(new Inventory("Ball"));
//            gameHibernateRepository.addGame(sportGame);
            gameHibernateRepository.deleteAllGames();
        } finally {
            session.close();
        }
    }
}