package snake.ladders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class createAccount {
    static Scanner in = new Scanner(System.in);
    static String name, phone, pass;
    
    static Boolean account(player[] objPlayer,int playerNumber){
        while(true){
            SnakeLadders.printTitle();
            System.out.println("PageName: AccountCreation");            
            System.out.println("Player: "+playerNumber);
            System.out.println(color.RED+"Type '0' to exit\n"+color.RESET);

            while(true){            
                System.out.print("Enter Name: ");
                name = in.nextLine();
                if(name.equals("0")) return false;
                if(!name.equals("") && checkCreateNameDetails()) break;
                System.out.println("\n\t***Invalid contact number or, Name already exist!");
            }
            while(true){            
                System.out.print("Enter phone number: +8801");
                phone = in.nextLine();
                if(phone.equals("0")) return false;
                if(phone.length()==9 && checkPhoneDetailsExistance()) break;         
                System.out.println("\n\t***Invalid contact number or, already exist***\n`"); 
            }
            while(true){            
                System.out.print("Enter pass(6digits): ");
                pass = in.nextLine();
                if(pass.equals("0")) return false;
                if(pass.length()==6) break;
                System.out.println("Something wrong!");
            }
            if(createAcount()){
                System.out.print("\n\nSuccessfully your account was created!\nUpdating page...");
                wait.time(4);
                objPlayer[playerNumber].playerName = name;           
                objPlayer[playerNumber].totalPoints = 0;
                return true;
            }
            else{
                System.out.println("\n\nSomething went wrong!\nTry Again! Updating page...");
                wait.time(4);
            }
        }
    }
    static Boolean checkCreateNameDetails(){
        String sql = "select * from accounts where name = '"+name+"'";
            try{
                Connection con;
                con = JDBC.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if(!rs.next())
                    {
                        con.close();
                        st.close();
                        rs.close();
                        return true;
                   }
            }
            catch(SQLException e){
                System.out.println("Error while connecting database.."+e.getMessage());
            }
        return false;
    }
    static Boolean checkPhoneDetailsExistance(){
        String sql = "select * from accounts where phone = '+8801"+phone+"'";
        try{
            Connection con;
                con = JDBC.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if(!rs.next())
                    {
                        con.close();
                        st.close();
                        rs.close();
                        return true;
                   }
            }
            catch(Exception e){
                System.out.println("Error while connecting database.."+e.getMessage());
            }
        return false;
    }
    static Boolean createAcount(){
        pass = encryptPass.encrypt(pass);
            try{
                Connection con;
                con = JDBC.getConnection();
                Statement st = con.createStatement();
                String queryToInsert = "insert into accounts values(default,'"+name+"', '+8801"+phone+"','"+pass+"', 0)";
//                String queryToInsert = "insert into accounts values(default,'"+name+"', '+8801"+phone+"','"+pass+"', 0, default)";

                st.execute(queryToInsert);
                
                con.close();
                st.close();
                return true;
            }
            catch(Exception e){
                System.out.println("Error while connecting database.."+e.getMessage());
            }
        return false;
    }
    static Boolean getIDofNewAccount(player[] objPlayer,int playerNumber){
        String sql = "select * from accounts where phone = "+phone+"";
        try{
            Connection con;
            con = JDBC.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                objPlayer[playerNumber].id = rs.getInt("id");
                con.close();
                st.close();
                rs.close();
                return true;
            }
        }
        catch(Exception e){
            System.out.println("Error while connecting database.."+e.getMessage());
        }
        return false;
    }
    static Boolean updateTotalPoints(int total, String name){
            try{
                Connection con;
                con = JDBC.getConnection();
                Statement st = con.createStatement();
                String queryToUpdate = "update accounts set totalPoints = "+total+" where name = '"+name+"'";
                st.execute(queryToUpdate);
                
                con.close();
                st.close();
                return true;
            }
            catch(SQLException e){
                System.out.println("Error while connecting database.."+e.getMessage());
            }
        return false;
    }
    public static void main(String[] args) {
        player obj[] = new player[2];
        obj[1] = new player();
        account(obj, 1);
    }
}
