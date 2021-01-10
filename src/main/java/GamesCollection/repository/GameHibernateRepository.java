package GamesCollection.repository;

import GamesCollection.connection.HibernateConnector;
import GamesCollection.games.Game;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class GameHibernateRepository implements Repository{

    private Session session = HibernateConnector.getSession();

    public GameHibernateRepository(Session session) {
        this.session = session;
    }

    @Override
    public boolean addGame(Game game) {
        Transaction tr = session.getTransaction();
        tr.begin();
        String existedGame = (String) session.createQuery("select name from Game where name = :name")
                .setParameter("name", game.getName()).uniqueResult();
        if(existedGame==null){
            session.save(game);
            tr.commit();
            return true;
        }
        tr.commit();
        return false;
    }

    @Override
    public boolean deleteGame(String name) throws SQLException {
        Transaction tr = session.getTransaction();
        tr.begin();
        Game removedGame = (Game) session.createQuery("from Game where name=:name")
                .setParameter("name", name.toUpperCase())
                .uniqueResult();
        if(removedGame==null){
            tr.commit();
            return false;
        }
        session.remove(removedGame);
        tr.commit();
        return true;
    }

    @Override
    public Set<Game> getAll() throws SQLException {
        Transaction tr = session.getTransaction();
        tr.begin();
        Set<Game> resultSet = (Set<Game>) session.createQuery("select g from Game g").getResultStream().collect(Collectors.toSet());
        if (resultSet!=null){
            tr.commit();
            return resultSet;
        }
        return null;
    }

    @Override
    public boolean deleteAllGames() throws SQLException {
        Transaction tr = session.getTransaction();
        tr.begin();
        long size = (long) session.createQuery("select count(Game) from Game").uniqueResult();
        if(size==0){
            tr.commit();
            return false;
        }
        session.createQuery("delete Game").executeUpdate();
        tr.commit();
        return true;
    }

    @Override
    public void exit() {
        session.close();
    }
}
