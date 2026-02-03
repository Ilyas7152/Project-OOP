package database;
import model.Customer;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {

    public void insertProduct(Product product) {
        String sql = "INSERT INTO PRODUCT (id,name, stock,price) VALUES (?, ?, ?, ?)";
        Connection connection =DatabaseConnection.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3,product.getStock());
            statement.setDouble(4, product.getPrice());


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Products inserted successfully");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }


    public void getAllProduct(){
        String sql = "SELECT * FROM product";
        Connection connection = DatabaseConnection.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet =statement.executeQuery();
            System.out.println("\n All products from database");
            while (resultSet.next()){
                double price = resultSet.getDouble("price");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int stock = resultSet.getInt("Stock");

                System.out.println("price: "+ price);
                System.out.println("id: "+ id);
                System.out.println("name: "+ name);
                System.out.println("stock: "+ stock);
            }
            resultSet.close();
            statement.close();
        }
        catch(SQLException e){
            System.out.println("Select failed");
            e.printStackTrace();
        } finally{
            DatabaseConnection.closeConnection(connection);
        }
    }


public boolean updateProduct(Product product) {
    String sql = "UPDATE product  SET Name = ?, id = ?," + "stock = ?, price=?" + "WHERE product_ID = ? AND product_type='Product'";
    Connection connection = DatabaseConnection.getConnection();
    if (connection == null) return false;
try {
    PreparedStatement statement=connection.prepareStatement(sql);
    statement.setDouble(1,product.getPrice());
    statement.setInt(2,product.getId());
    statement.setInt(3,product.getStock());
    statement.setString(4,product.getName());
    int rowsUpdated = statement.executeUpdate();
    statement.close();
    if(rowsUpdated>0){
        System.out.println("product updated"+ product.getName());
        return true;
    }
} catch (SQLException e){
    System.out.println("Update failed");
    e.printStackTrace();

}finally {
    DatabaseConnection.closeConnection(connection);
}
return false;
}}
