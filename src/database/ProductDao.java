package database;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {


    public void createProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (id, name, stock, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getStock());
            statement.setDouble(4, product.getPrice());

            statement.executeUpdate();
        }
    }


    public List<Product> readAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        }

        return products;
    }


    public boolean updateProductBasic(int id, String name, int stock, double price) throws SQLException {
        String sql = "UPDATE product SET name = ?, stock = ?, price = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, stock);
            statement.setDouble(3, price);
            statement.setInt(4, id);

            return statement.executeUpdate() > 0;
        }
    }


    public boolean deleteProductById(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Product> searchByName(String name) throws SQLException {
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(extractProductFromResultSet(rs));
                }
            }
        }
        return products;
    }
    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM product " +
                "WHERE price BETWEEN ? AND ? " +
                "ORDER BY price ASC";

        try {
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                productList.add(product);
            }

            resultSet.close();
            statement.close();
            DatabaseConnection.closeConnection(connection);

            System.out.println("✅ Found " + productList.size() + " products");

        } catch (SQLException e) {
            System.out.println("❌ Search failed");
            e.printStackTrace();
        }

        return productList;
    }
    public List<Product> searchByMinPrice(double minPrice) {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM product " +
                "WHERE price >= ? " +
                "ORDER BY price ASC";

        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) return productList;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                if (product != null) {
                    productList.add(product);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return productList;
    }


    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");

                return new Product(price, id, name, stock);

        }}