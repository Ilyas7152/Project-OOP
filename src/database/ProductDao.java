package database;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
public void createProduct (Product product) throws SQLException{
    String sql = "INSERT INTO product (id,name,stock,price ) VALUES(?,?,?,?)";

    try   (Connection connection = DatabaseConnection.getConnection();
           PreparedStatement statement= connection.prepareStatement(sql)){
        statement.setInt(1,product.getId());
        statement.setString(2, product.getName());
        statement.setInt(3,product.getStock());
        statement.setDouble(4,product.getPrice());
        statement.executeUpdate();
    }


}
public List<Product> readAllProducts()throws SQLException{
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM product ORDER BY id";
    try(Connection connection=DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery()){
        while(rs.next()){
            products.add(extractProductFromResultSet(rs));
        }
    }return products;
}
public boolean updateProductBasic(int id,String name,int stock,double price)throws SQLException{
    String sql = "UPDATE product SET name=?,stock=?,price=? WHERE id=?";
    try(Connection connection=DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

        statement.setString(1,name);
        statement.setInt(2,stock);
        statement.setDouble(3,price);
        statement.setInt(4,id);
        return statement.executeUpdate()>0;
    }

}
public boolean deleteProductById(int id)throws SQLException{
    String sql="DELETE FROM product WHERE id =?";
    try(Connection connection=DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1,id);
        return statement.executeUpdate()>0;
    }
}


private Product extractProductFromResultSet(ResultSet resultSet)throws SQLException{
    int id =resultSet.getInt("id");
    String name=resultSet.getString("name");
    int stock =resultSet.getInt("stock");
    double price= resultSet.getDouble("price");
    return new Product(price,id,name,stock);
}
    }
