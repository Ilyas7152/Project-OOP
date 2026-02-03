package database;
import database.DatabaseConnection;


import java.sql.Connection;

public class TestConnection{
    public static void main(String[] args){
        Connection connection = DatabaseConnection.getConnection();
        if (connection !=null){
            System.out.println("Connected");
            DatabaseConnection.closeConnection(connection);
        } else{
            System.out.println("Connection failed");
        }}}
