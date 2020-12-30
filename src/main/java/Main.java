import GamesCollection.entity.Inventory;
import GamesCollection.entity.SportGameEntity;
import GamesCollection.entity.VideoGameEntity;
import GamesCollection.games.VideoGame;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

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
            SportGameEntity game = new SportGameEntity();
            game.setName("Football");
            game.setNumberOfPlayers(22);
            session.beginTransaction();
            session.save(game);
            session.getTransaction().commit();


        } finally {
            session.close();
        }
    }
}