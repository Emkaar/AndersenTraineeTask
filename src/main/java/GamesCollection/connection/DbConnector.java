package GamesCollection.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnector {
    private static HikariDataSource dataSource;
    static {
        dataSource = new HikariDataSource(new HikariConfig("/db.properties"));
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void closeConnections(){
        dataSource.close();
    }
}
