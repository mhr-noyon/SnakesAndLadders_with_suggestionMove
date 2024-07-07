package snake.ladders;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//create database snakes_and_ladders;
//create table accounts(
//    id int primary key AUTO_INCREMENT,
//    name varchar(100) not null UNIQUE,
//    phone varchar(15) not null unique,
//    pass varchar(100) not null,
//    totalPoints int default 0
//)
public class JDBC {
    static String dbURL = "jdbc:mysql://localhost:3306/";
    static String dbName = "snakes_and_ladders";
    static  String user = "root";
    static  String pass = "";
          
    public static Connection getConnection(){
        Connection connect = null;
        try{
            connect = DriverManager.getConnection(dbURL, user, pass);
            String queryToCreateDB = "CREATE DATABASE IF NOT EXISTS " + dbName;
            try (Statement st = connect.createStatement()) {
                st.executeUpdate(queryToCreateDB);
            } catch (SQLException e) {
                System.out.println("Error while creating Database: " + e.getMessage());
            }
            connect.close(); // Close the initial connection

            // Connect to the newly created database
            connect = DriverManager.getConnection(dbURL + dbName, user, pass);
            String queryToCreateTable = "CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(100) NOT NULL UNIQUE, "
                    + "phone VARCHAR(15) NOT NULL UNIQUE, "
                    + "pass VARCHAR(100) NOT NULL, "
                    + "totalPoints INT DEFAULT 0)";
            try (Statement st = connect.createStatement()) {
                st.executeUpdate(queryToCreateTable);
            } catch (SQLException e) {
                System.out.println("Error while creating Table: " + e.getMessage());
            }
        }
        catch(SQLException e){
            System.out.println("Error while connecting database.."+e.getMessage());
        }
        return connect;
    }
    
    
    static void updateTotalPoints(player[] obj,int player){
            try{
                Connection con;
                con = JDBC.getConnection();
                try (Statement st = con.createStatement()) {
                    String queryToInsert = "update accounts set totalpoints = "+obj[player].totalPoints+" where name = '"+obj[player].playerName+"'";
                    st.execute(queryToInsert);
                    
                    con.close();
                }
            }
            catch(SQLException e){
                System.out.println("Error while connecting database.."+e.getMessage());
            }
    }
    static void updatePlaying(player[] obj,int player){
            try{
                Connection con;
                con = JDBC.getConnection();
                try (Statement st = con.createStatement()) {
                    String queryToInsert = "update accounts set playing = 'false' where name = '"+obj[player].playerName+"'";
                    st.execute(queryToInsert);
                    
                    con.close();
                }
            }
            catch(SQLException e){
                System.out.println("Error while connecting database.."+e.getMessage());
            }
    }
}
