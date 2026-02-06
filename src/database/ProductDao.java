package database;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
public void  createProduct(Product product)throws SQLException {
    String sql = "INSERT INTO product (id,name,stock,price) VALUES(?,?,?,?)";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, product.getId());
        statement.setString(2, product.getName());
        statement.setInt(3, product.getStock());
        statement.setDouble(4, product.getPrice());
        statement.executeUpdate();
    }
}
public List<Product> readAllProducts()throws SQLException{
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM product ";
    try(Connection connection=DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery()){
            while (rs.next()){
            products.add(extractProductFromResultSet(rs));}
        }
     return products;
}

public boolean updateProductBasic(int id,String name,int stock,double price)throws SQLException{
    String sql = "UPDATE product SET name=?,stock=?,price=? WHERE id=?";
    try (Connection connection=DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,name);
        statement.setInt(2,stock);
        statement.setDouble(3,price);
        statement.setInt(4,id);
        return statement.executeUpdate()>0;
    }

}
public boolean deleteProductById(int id)throws SQLException{
    String sql = "DELETE FROM product WHERE id =?";
    try(Connection connection=DatabaseConnection.getConnection();
        PreparedStatement statement= connection.prepareStatement(sql)){
        statement.setInt(1,id);
        return statement.executeUpdate()>0;
    }
}
public List <Product> searchByName(String name){
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY id ASC";
    try{Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);{
        statement.setString(1,"%"+ name +"%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            products.add(extractProductFromResultSet(rs));
            System.out.println("Products found :"+ products.size());
        }
        statement.close();
        rs.close();
        DatabaseConnection.closeConnection(connection);
        }

    }
    catch (SQLException e){
        e.printStackTrace();
        System.out.println("Search failed");
    }

        return products;

}
public List <Product> searchByPriceRange(double min,double max) {
    List<Product> products=new ArrayList<>();
    String sql ="SELECT * FROM product WHERE price BETWEEN ? AND ?";
    try {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, min);
        statement.setDouble(2, max);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {

            products.add(extractProductFromResultSet(rs));
        }
        statement.close();
        rs.close();
        DatabaseConnection.closeConnection(connection);
    }
    catch (SQLException e){
        System.out.println("Search failed");
        e.printStackTrace();
    } return products;
}
 public List <Product> searchByMinPrice(double minPrice){
    List<Product> products = new ArrayList<>();
    String sql="SELECT * FROM product WHERE price >=?";
    try{Connection connection =DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1,minPrice);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            products.add(extractProductFromResultSet(rs));
        }statement.close();
        rs.close();
        DatabaseConnection.closeConnection(connection);
    }catch (SQLException e){
        e.printStackTrace();
        System.out.println("Search failed");
    }
    return products;
 }
private Product extractProductFromResultSet(ResultSet resultSet)throws SQLException{
    int id =resultSet.getInt("id");
    String name=resultSet.getString("name");
    int stock =resultSet.getInt("stock");
    double price= resultSet.getDouble("price");
    return new Product(price,id,name,stock);
}
    }
