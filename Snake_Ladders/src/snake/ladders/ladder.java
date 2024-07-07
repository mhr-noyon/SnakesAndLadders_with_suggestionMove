package snake.ladders;

import java.util.Scanner;

public class ladder {
    static Scanner in = new Scanner(System.in);
    int to;
//    static int[] ladders;
    
    static int[] fixLadders(){
        Board.gridSize = 10;
        int maxCell = Board.gridSize*Board.gridSize;
        int[] ladders = new int[maxCell+1];
        for(int i=1;i<=maxCell;i++){
            ladders[i] = 0;
        }
        ladders[3] = 20;        
        ladders[6] = 14;
        ladders[11] = 28;
        ladders[15] = 34;
        ladders[17] = 74;
        ladders[22] = 37;
        ladders[38] = 59;
        ladders[49] = 67;
        ladders[57] = 76;
        ladders[61] = 78;
        ladders[73] = 86;
        ladders[81] = 98;
        ladders[88] = 91;
        return ladders;
    }   
    static int[] dynamicLadders(){
        System.out.print("Enter the size of grid(AxA) A: ");
        Board.gridSize = in.nextInt();
        int maxCell = Board.gridSize * Board.gridSize;
        Board.Lsize = maxCell+1;
        while(Board.Lsize>maxCell){
            System.out.print("Enter how many ladders will be there: ");
            Board.Lsize = in.nextInt(); 
            if(Board.Lsize<=maxCell)break;
            System.out.println("\n\nInput doesn't match game rule..");
        }
        int[] ladders = new int[maxCell+1]; 
        for(int i=1;i<=maxCell;i++){
            ladders[i] = 0;
        }
        for(int i = 1;i<=Board.Lsize;i++){   
            System.out.println("----------------------------------------------"); 
            System.out.print("Input ladders livingIndex: ");
            int from = in.nextInt(); 
            System.out.print("Input ladders targetIndex: ");
            int to = in.nextInt();
            
            if(from>=to || from>maxCell) 
                while(true){
                   System.out.println("Input doesn't match game rule..");
                   System.out.print("Input ladders livingIndex: ");
                   from = in.nextInt(); 
                   System.out.print("Input ladders targetIndex: ");
                   to = in.nextInt();
                   if(from<to && from<=maxCell) break;               
                }               
            ladders[from] = to;
        }
        return ladders;
    } 
    public static void main(String[] args) {
        int[] ladders = fixLadders();
        for(int i=1;i<=Board.gridSize*Board.gridSize;i++){
            if(ladders[i]!=0)
                System.out.println(i+" "+ladders[i]);
        }
        ladders = dynamicLadders();
        for(int i=1;i<=Board.gridSize*Board.gridSize;i++){
            if(ladders[i]!=0)
                System.out.println(i+" "+ladders[i]);
        }
    }
}
