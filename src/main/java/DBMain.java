import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMain {
    public static final String DB_USER_NAME = "root";
    public static final String DB_PASSWORD = "45673756";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/games";
    public static final String TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(DB_URL + TIME_ZONE, DB_USER_NAME, DB_PASSWORD)){
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Woo-hoo");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
