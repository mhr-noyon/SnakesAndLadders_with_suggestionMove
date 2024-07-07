package snake.ladders;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner in = new Scanner(System.in);
    static char[] logo = {' ', 'W', 'B', 'G', 'Y'};
    
    Game(int numOfPlayer){
        
        int maxPosition;
            int maxPositionHolder;
            int countHolder;
        player[] objPlayer = new player[numOfPlayer+1];
        objPlayer[0] = new player();
        System.out.print("\n\tFix the move limit per player: ");
        int limit = in.nextInt() * numOfPlayer;
        SnakeLadders.in.nextLine();
         
        for(int i=1;i<=numOfPlayer;i++){
            objPlayer[i] = new player();
            if(!player.PlayerProfile(objPlayer, i)){
                System.out.println(color.RED+"\n\nPlayer "+i+" refused to play.\n"
                   + "Going back to the HomePage...!"+color.RESET);
                wait.time(4);
                return;
            }
            objPlayer[i].player = i;            
            objPlayer[i].moveLeft = limit/numOfPlayer;
            objPlayer[i].icon = logo[i];
            objPlayer[i].position = 0;            
            objPlayer[i].diceNum[0] = 0;            
            objPlayer[i].diceNum[1] = 0;            
            objPlayer[i].diceNum[2] = 0;
        }
        for(int i=1;i<=numOfPlayer;i++){
                JDBC.updatePlaying(objPlayer,i);
         }
        SnakeLadders.printTitle();
        System.out.println("Players are: "); 
        for(int i=1;i<=numOfPlayer;i++){
            System.out.println("Player "+i+": "+color.GREEN+
                    objPlayer[i].playerName+" ("+logo[i]+")"+color.RESET);
        }
        System.out.println("\n\nStarting the game...");
        wait.time(6);
        SnakeLadders.printTitle();        
        
        int turn = 1;
        Random dice = new Random();
        
        Boolean won = false;
        while(limit>0){
            
            for(int i=1;i<=numOfPlayer;i++){
                System.out.println("Player "+i+"("+color.BLUE+
                        objPlayer[i].playerName+color.RESET+"-"+
                        objPlayer[i].icon+") : "+objPlayer[i].position);
            }
            limit--;
            if(turn>numOfPlayer) turn = 1;
            System.out.println("\n\nNow turn: "+color.Magenta+
                    objPlayer[turn].playerName+" ("+objPlayer[turn].icon+")"+color.RESET);           
            System.out.println("\nMove left: "+color.RED+objPlayer[turn].moveLeft+
                    color.RESET);            
            try {
                 shortestWayToWin shortestMoves = new shortestWayToWin();            
                //for required moves print
                int[] snakes = snake.fixSnakes();
                int[] ladders = ladder.fixLadders();            
                int[] reqMove = new int[100];
                int countReMove = 0;
                int prev[] = shortestWayToWin.shortestWay(snakes,ladders,
                        objPlayer[turn].position);
                int cell = 100;
                System.out.println(color.Magenta+"\nSuggestions positions: ");
                int[] expectedPosition = new int[100];
                int c=0;
                while(cell!=objPlayer[turn].position){
                    expectedPosition[c] = cell;
                    c++;
                    cell = prev[cell];
                }        
                int curP = objPlayer[turn].position;
                for(int i=c-1;i>=0;i--){
                    Boolean flag = false;
                    for(int j=1;j<=6;j++){
                        if(curP+j>Board.gridSize*Board.gridSize) break;
                        if(ladders[curP+j]==expectedPosition[i]){
                            System.out.print((curP+j)+" - "); 
                            curP = ladders[curP+j];
                            flag = true;
                            break;
                        }
                        else if(snakes[curP+j]==expectedPosition[i]){
                            System.out.print((curP+j)+" - "); 
                            curP = snakes[curP+j];
                            flag = true;
                            break;
                        }
                    }
                    if(!flag)
                        System.out.print(expectedPosition[i]+" - ");
                }
                curP = objPlayer[turn].position;
                System.out.println(color.Magenta+"\nDice required: ");
                for(int i=c-1;i>=0;i--){
                    Boolean flag = false;
                    for(int j=1;j<=6;j++){
                        if(curP+j>Board.gridSize*Board.gridSize) break;
                        if(ladders[curP+j]==expectedPosition[i]){
                            System.out.print(j+" - "); 
                            curP = ladders[curP+j];
                            flag = true;
                            break;
                        }
                        else if(snakes[curP+j]==expectedPosition[i]){
                            System.out.print(j+" - "); 
                            curP = snakes[curP+j];
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){                        
                        System.out.print((expectedPosition[i]-curP)+" - ");
                        curP = expectedPosition[i];
                    }
                }
            } catch (Exception e) {
                System.out.println("Error in bfs code"+e.getMessage());
            }
            
            objPlayer[turn].moveLeft--;
            
            System.out.print(color.RED+"\n\nPress "+color.GREEN+"'enter'"+color.RESET+" key to roll the dice: ");
            SnakeLadders.in.nextLine();
            int move = (dice.nextInt(6)+1);  

            Board board1 = new Board(numOfPlayer, turn);           
            int c = 1;
            if(move==1){
                System.out.println("Dice number is: "+ move);
                System.out.println("\nBoard upadting: ");
                    wait.time(4);
                    Board.run(objPlayer);
                while(move==1){
                    if(objPlayer[turn].position==100){
                        System.out.println(color.GREEN+"Player "+
                                objPlayer[turn].player+" won!"+color.RESET);
                        won = true;
                        break;
                    }                     
                    if(c==3){              
                        System.out.println("\nMove is cancelled as 3 ones occured");
                        break;
                    }                

                    System.out.print("\nYou had a '1' so,Again, Press "+
                            color.GREEN+"'enter'"+color.RESET+" key to roll the dice: ");
                    SnakeLadders.in.nextLine();
                    move = (dice.nextInt(6)+1);
                    System.out.print("\nDice number is: "+ move);
                    if(objPlayer[turn].tempPosition+move>100){
                        System.out.println("\nMove is cancelled as points exceeded 100");
                        move = 0;
                        break;
                    } 
                    objPlayer[turn].tempPosition += move;            

                    System.out.println("\nBoard upadting: ");
                    wait.time(4);
                    Board.run(objPlayer);
                    c++;
                }
            }
            else{                
                System.out.println("Dice number is: "+ move);
                if(objPlayer[turn].position+move>100){
                    System.out.println("\nMove is cancelled as points exceeded 100");
                    move = 0;
                    continue;
                } 
                else{
                    objPlayer[turn].tempPosition += move;           
                    System.out.println("\nBoard upadting: ");
                    wait.time(4);
                    Board.run(objPlayer);
                }                
            }
            if(move!=0)
                objPlayer[turn].position = objPlayer[turn].tempPosition;
            else{
                System.out.println("\nMove was cancelled as points exceeded"
                        + " 100 or 3 ones got");
                                 
                objPlayer[turn].tempPosition = objPlayer[turn].position;

//                Board board1 = new Board(numOfPlayer, turn);            
                        //objPlayer[turn].position = board1.run(objPlayer);  
                System.out.println("\nBoard upadting: ");
                wait.time(4);
                Board.run(objPlayer);
            }
        
            if(objPlayer[turn].position==100){
                System.out.println(color.GREEN+"Player "+objPlayer[turn].player+
                        " won!"+color.RESET);
                won = true;
            }               
            turn++;            
        }
             maxPosition = objPlayer[1].position;
             maxPositionHolder = 1;
             countHolder = 1;
            
            for(int i=2;i<=numOfPlayer;i++){
                if(maxPosition<objPlayer[i].position){
                    maxPosition = objPlayer[i].position;
                    maxPositionHolder = i;
                    countHolder = 1;
                }
                else if(maxPosition == objPlayer[i].position) countHolder++;
            }
        if(countHolder==1){
            System.out.println("");            
            objPlayer[maxPositionHolder].totalPoints += 2;
            System.out.println(color.RED+"\n\n"+objPlayer[maxPositionHolder].playerName+
                    " won the match!"+color.RESET);
            JDBC.updateTotalPoints(objPlayer, maxPositionHolder);
        }
        else if(countHolder>1){
            System.out.println(color.RED+"\n\nMatch drawn!"+color.RESET);
            
        }
//        Set they are not playing now:
//         for(int i=1;i<=numOfPlayer;i++){
//                JDBC.updatePlaying(objPlayer,i);
//         }
        wait.time(6);
//        board1.running(objPlayer);
    }
}
