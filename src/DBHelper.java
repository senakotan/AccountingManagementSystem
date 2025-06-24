
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/accounting_db";
    private static final String USER = "root"; //veritabanı kullanıcısı
    private static final String PASSWORD = "admin"; // sifrem admin

    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
