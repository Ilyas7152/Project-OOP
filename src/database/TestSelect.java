package database;

import java.sql.SQLException;

public class TestSelect {
    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        try {
            dao.readAllProducts().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
