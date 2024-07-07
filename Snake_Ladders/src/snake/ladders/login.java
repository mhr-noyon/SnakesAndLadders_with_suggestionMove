package snake.ladders;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class login {
    static String name, pass;
    static Scanner in = new Scanner(System.in);
    
    static Boolean account(player[] objPlayer,int playerNumber){
        while(true){
            SnakeLadders.printTitle();
            System.out.println("PageName: Login");            
            System.out.println("Player: "+playerNumber);
            System.out.println(color.RED+"Type '0' to exit\n"+color.RESET);

            while(true){            
                System.out.print("Enter name: ");
                name = in.nextLine();
                if(name.equals("0")) return false;
                if(name.isEmpty())
                    System.out.println("\n\n***No name found,\n\n ");
                else break;
            }
            while(true){            
                System.out.print("Enter password: ");
                pass = in.nextLine();
                if(pass.equals("0")) return false;
                if(pass.isEmpty()) System.out.println("\n\n***No input found,\n\n ");
                else if(pass.length()!=6) System.out.println("\n\n**Six digit"
                        + " password required!");
                else break;
            }

            if(checkLoginDetails(objPlayer, playerNumber)){
                System.out.println("\n\nLogged in successfully!\nUpdating page...");
                wait.time(3);
                return true;
            }
            else{
                System.out.print("\n\nWrong id or password.\n Try again!"
                        + " Updating page...\n\n\n");
                wait.time(3);            
            }
        }
    }
    static Boolean checkLoginDetails(player[] objPlayer,int playerNumber){
        pass = encryptPass.encrypt(pass);
        String sql = "select * from accounts where name = '"+name+"' and pass = '"+pass+"'";
        Connection con;
        Statement st;
        ResultSet rs;
        try{
            con = JDBC.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                objPlayer[playerNumber].id = rs.getInt("id");                
                objPlayer[playerNumber].playerName = rs.getString("name");
                objPlayer[playerNumber].totalPoints = rs.getInt("totalPoints");
//                String s = rs.getString("playing");
//                if(s.equals("true")) return false;
                con.close();
                st.close();
                rs.close();
                return true;
            }
            else{
                con.close();
                st.close();
                rs.close();
                return false;
            }
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
