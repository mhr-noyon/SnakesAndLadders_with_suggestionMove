package snake.ladders;

import java.util.Scanner;

public class snake {
    static Scanner in = new Scanner(System.in);
//    int from;
    int to;
    static int[] fixSnakes(){
        Board.gridSize = 10;
        int maxCell = Board.gridSize*Board.gridSize;
        int[] snakes = new int[maxCell+1];        
        for(int i=1;i<=maxCell;i++){
            snakes[i] = 0;
        }
        snakes[8] = 4;        
        snakes[18] = 1;
        snakes[26] = 10;
        snakes[39] = 5;
        snakes[51] = 6;
        snakes[54] = 36;
        snakes[60] = 23;
        snakes[75] = 28;
        snakes[83] = 45;
        snakes[90] = 48;
        snakes[92] = 25;
        snakes[97] = 87;
        return snakes;
    }   
    static int[] dynamicSnakes(){
        System.out.print("Enter the size of grid(AxA) A: ");
        Board.gridSize = in.nextInt();
        int maxCell = Board.gridSize * Board.gridSize;
        Board.Ssize = maxCell+1;
        while(Board.Ssize>maxCell){
            System.out.print("Enter how many snakes will be there: ");
            Board.Ssize = in.nextInt(); 
            if(Board.Ssize<=maxCell)break;
            System.out.println("\n\nInput doesn't match game rule..");
        }
        int[] snakes = new int[maxCell+1]; 
        for(int i=1;i<=maxCell;i++){
            snakes[i] = 0;
        }
        for(int i = 1;i<=Board.Ssize;i++){   
            System.out.println("----------------------------------------------"); 
            System.out.print("Input snakes livingIndex: ");
            int from = in.nextInt(); 
            System.out.print("Input snakes targetIndex: ");
            int to = in.nextInt();
            
            if(from<=to || from>maxCell) 
                while(true){
                   System.out.println("Input doesn't match game rule..");
                   System.out.print("Input snakes livingIndex: ");
                   from = in.nextInt(); 
                   System.out.print("Input snakes targetIndex: ");
                   to = in.nextInt();
                   if(from>to && from<=maxCell) break;               
                }               
            snakes[from] = to;
        }
        return snakes;
    } 
    public static void main(String[] args) {
        int[] snakes = fixSnakes();
        for(int i=1;i<Board.gridSize*Board.gridSize;i++){
            if(snakes[i]!=i)
                System.out.println(i+" "+snakes[i]);
        }
        snakes = dynamicSnakes();
        for(int i=1;i<Board.gridSize*Board.gridSize;i++){
            if(snakes[i]!=0)
                System.out.println(i+" "+snakes[i]);
        }
    }
    
}
