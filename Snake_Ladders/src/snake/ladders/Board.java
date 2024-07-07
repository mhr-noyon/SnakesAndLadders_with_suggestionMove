package snake.ladders;

import java.util.Scanner;

public class Board {
    snake[] snakes;
    ladder[] ladders;
    static int numOfPlayer, currentPlayer, updatedPosition;
    static int changeAble;
    static Scanner in = new Scanner(System.in);
//    void fixBoard(String s){
//        if(s.equals("fixed")){
//            snakes = snake.fixSnakes();
//            ladders = ladder.fixLadders();
//        }
//        else{
//            snakes = snake.fixSnakes();
//            ladders = ladder.fixLadders();
//        }
//    }
    Board(int numOfPlayer, int currentPlayer){
        this.numOfPlayer = numOfPlayer;        
        this.currentPlayer = currentPlayer;
        this.changeAble = 0;  
    }
    
    static int run(player[] objPlayer){               
        updatedPosition = objPlayer[currentPlayer].tempPosition;
        
        showBoard(objPlayer);
        if(changeAble!=0){
            if(changeAble==1)
                System.out.println(color.RED+"\nA snake bites you!"+color.RESET);
            else
                System.out.println(color.GREEN+"\nA ladder found!"+color.RESET);
            System.out.println("Updating Board...");
            wait.time(4);
            objPlayer[currentPlayer].tempPosition = updatedPosition;
            showBoard(objPlayer);
        }
        return 1;
    }
    
    
    static int gridSize, Ssize , Lsize ;    
    static void showBoard(player[] objPlayer){     
        String s="fixed";
        gridSize = 10;
        int[] snakes = snake.fixSnakes();
        int[] ladders = ladder.fixLadders();
        String space = "        ";    
        System.out.println("");
        System.out.println("---------------------------------------"
                + "----------------------------------------------------"); 
        for(int i = gridSize-1;i>=0;i--){   
            for(int j=gridSize;j>=1;j--){
                System.out.print("|");
                int pos = i*gridSize+j;
                if(pos<10) System.out.print("    "+pos+"   ");
                else if(pos<100) System.out.print("   "+pos+"   ");
                else System.out.print("  "+pos+"   ");
            }
            System.out.println("|");            
            //features
            for(int j=gridSize;j>=1;j--){
                int pos = i*gridSize+j;
                System.out.print("|");
                if(snakes[pos]!=0){                    
                    if(objPlayer[currentPlayer].tempPosition==pos){
                        changeAble = 1;
                        updatedPosition = snakes[pos];
                    }
                    if(snakes[pos]>=10)
                        System.out.print(color.RED+"  "+"S_"+snakes[pos]+"  "+color.RESET);                    
                    else 
                        System.out.print(color.RED+"  "+"S_0"+snakes[pos]+"  "+color.RESET);
//                     
                }
                else if(ladders[pos]!=0){
                    if(objPlayer[currentPlayer].tempPosition==pos){
                        changeAble = 2;
                        updatedPosition = ladders[pos];
                    }
                    if(ladders[pos]>=10)
                        System.out.print(color.GREEN+"  "+"L_"+ladders[pos]+"  "+color.RESET);                    
                    else 
                        System.out.print(color.GREEN+"  "+"L_0"+ladders[pos]+"  "+color.RESET);
//                    ladderIndVisit--;
                }
                else System.out.print(space);
            }
            System.out.println("|");
            
            
            //print players position              
            for(int j=gridSize;j>=1;j--){
                int foundPlayer = 0;
                System.out.print("|");
                int pos = i*gridSize+j;    
                for(int k = 1;k<=numOfPlayer;k++){
                    if(objPlayer[k].tempPosition==pos){
                        foundPlayer++;
                        System.out.print(objPlayer[k].icon+" ");
                    }
                }
                if(foundPlayer==0) System.out.print(space);                
                else if(foundPlayer==1) System.out.print("      ");                
                else if(foundPlayer==2) System.out.print("    ");                
                else if(foundPlayer==3) System.out.print("  ");
            }
            System.out.println("|");
            System.out.println("-------------------------------------"
                    + "------------------------------------------------------");
        }
    }
    public static void main(String[] args) {
        //for testing
        player[] players = new player[3];
        players[0] = new player();
        players[0].tempPosition = 1;
        players[0].icon ='Q';
        players[1] = new player();
        players[1].tempPosition = 50;
        players[1].icon ='Y';
        players[2] = new player();
        players[2].tempPosition = 99;
        players[2].icon ='R';
        Board obj = new Board(2,1);
        run(players);
    }
}
