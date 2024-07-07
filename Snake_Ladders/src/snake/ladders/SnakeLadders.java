package snake.ladders;
import java.util.*;

public class SnakeLadders {
    static Scanner in = new Scanner(System.in);
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
     }
    static void printTitle(){
        newPageDesign();
        System.out.println("\n-------------------------------------------");
        System.out.println("            Snakes and Ladders");
        System.out.println("-------------------------------------------\n\n");
    }
    static void newPageDesign(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        for(int i = 0; i < 30; i++)
            System.out.print("\f"); 
    }
    public static void main(String[] args) {  
        wait.time(4);
        
        while(true){      
            SnakeLadders.printTitle();        
            Boolean numOfPlayerFlag = true;
            System.out.print("\nTo Begin a game,\n");            
            System.out.print("      Enter number of player between 2 to 4: ");

            while(numOfPlayerFlag){
                int n = in.nextInt();
                if(n>=2 && n<=4){
                   
                    Game start = new Game(n);
                    numOfPlayerFlag = false;
                }
                else{
                    System.out.print("\n\nNumber of player should not be this.\n"
                            + "\tAgain, enter number of player (between 2 to 4): ");
                }
            }
        }       
//        board obj1 = new board(1);               
    }

}
