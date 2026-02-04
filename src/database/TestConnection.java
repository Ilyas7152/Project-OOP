package database;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        System.out.println("Connected ✅");
        connection.close();
    }
}
