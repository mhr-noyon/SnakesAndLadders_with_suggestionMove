package snake.ladders;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class shortestWayToWin {
    static final int N = 1005; // Adjust the value according to your needs
    static final int INF = 1000010; // Adjust the value according to your needs
    static ArrayList<Boolean> visited = new ArrayList<>(N);
    static ArrayList<Integer> move_req = new ArrayList<>(N);
   

    static class Pair {
        int distance, cell;

        Pair(int distance, int cell) {
            this.distance = distance;
            this.cell = cell;
        }
    }

    static int[] shortestWay(int[] snakes, int[] ladders, int position) { 
//        System.out.println("called");
        move_req = new ArrayList<>(100 + 1);
        visited = new ArrayList<>(100 + 1);
        int prev[] = new int[101];
        for (int i = 0; i <= 100; i++) {
            visited.add(false);
            move_req.add(INF);
            prev[i]=-1;
        }       
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> 
                Integer.compare(a.distance, b.distance));
        
        move_req.set(position, 0);
        pq.add(new Pair(move_req.get(position), position));

        while (!pq.isEmpty()) {
            Pair top = pq.remove();
            int cur_cell = top.cell;
            visited.set(cur_cell, true);
            
            for (int i=1;i<=6;i++) {
                int new_cell = cur_cell+i;
                if(new_cell>100) break;
                if (!visited.get(new_cell)) {                    
                    if (move_req.get(new_cell) > move_req.get(cur_cell) + 1) {
                        if (snakes[new_cell] != 0){
                            visited.set(new_cell, true);
                            new_cell = snakes[new_cell];                        
                            prev[snakes[new_cell]] = cur_cell;
                        }
                        else if (ladders[new_cell] != 0){
                            visited.set(new_cell, true);
                            new_cell = ladders[new_cell];                                                
                            prev[ladders[new_cell]] = cur_cell;
                        }
                        else{                                              
                            prev[new_cell] = cur_cell;
                        }
                        move_req.set(new_cell, move_req.get(cur_cell) + 1);
                        pq.add(new Pair(move_req.get(new_cell), new_cell));
                        prev[new_cell] = cur_cell;
                    }
                }
            }
        }
        visited.clear();
        move_req.clear();
        
        return prev;
    }
    public static void main(String[] args) {
        //without ladder / 
        try {
                 shortestWayToWin shortestMoves = new shortestWayToWin();            
                //for required moves print
                int[] snakes = snake.fixSnakes();
                int[] ladders = ladder.fixLadders();            
                int[] reqMove = new int[1001];
                int countReMove = 0;
                int[] prev = shortestWayToWin.shortestWay(snakes,ladders,3);
                int cell = 100;
                System.out.println("printing move: ");
                while(prev[cell]!=3){
                    System.out.print(cell+" ");
                    cell = prev[cell];
                    countReMove++;
                }       
                System.out.println("\n\n");
//                System.out.println(color.YELLOW+"\nMove suggestions: ");
//                for(int i=countReMove-1;i>=0;i--){
//                    System.out.print(reqMove[i]+" ");
//                }
            } catch (Exception e) {
                System.out.println("Error in bfs code"+e.getMessage());
            }
    }
}
//public class shortestWayToWin {
//    class node{
//        int node;
//        int moveReq;
//    }
//    int[] bfs(int[] snakes, int[] ladders, int pos){        
//        int maxCell = Board.gridSize*Board.gridSize;
//        Boolean[] visited = new Boolean[101];        
//        int[] prev = new int[101];
//        for(int i=1;i<=maxCell;i++){
//            prev[i] = -1;
//            visited[i] = false;
//        }
//        System.out.println("snakes: ");
//        for(int i=1;i<=100;i++){
//            if(snakes[i]!=0)
//            System.out.print(i+" ");
//        }
//        System.out.println("ladders: ");
//        for(int i=1;i<=100;i++){
//            if(ladders[i]!=0)
//            System.out.print(i+" ");
//        }
//        System.out.println("\n\n");
//        
//        Queue<node> q= new LinkedList<>();
//        node curCellNode = new node();
//        curCellNode.node = pos;
//        curCellNode.moveReq = 0;
//        prev[curCellNode.node] = -1;
//        q.add(curCellNode);
//        while (!q.isEmpty()) {
//            curCellNode = q.remove();            
////            System.out.println(curCellNode.node);
//            int curCell = curCellNode.node;
// 
//            if (curCell == maxCell)
//                break;
//            
//            for (int adjCell = curCell + 1; adjCell <= (curCell + 6); adjCell++) {
//                if(adjCell > maxCell) break;
//                if (visited[adjCell] == false) {
//                    node newCellNode = new node();
//                    newCellNode.moveReq = curCellNode.moveReq + 1;
//                    visited[adjCell] = true;
//                    if (snakes[adjCell] != 0){
//                        newCellNode.node = snakes[adjCell];                        
//                        prev[snakes[adjCell]] = curCell;
//                    }
//                        
//                    else if (ladders[adjCell] != 0){
//                        newCellNode.node = ladders[adjCell];                                                
//                        prev[ladders[adjCell]] = curCell;
//                    }
//                    else{
//                         newCellNode.node = adjCell;                                               
//                        prev[adjCell] = curCell;
//                    }
//                       
//                    System.out.println("Adding "+curCell+" to :"+newCellNode.node);
//                    q.add(newCellNode);
//                }
//            }
//        }
//        for(int i=pos;i<=100;i++){
//            System.out.println("Prev of "+i+": "+prev[i]);
//        }
//        
////        for(int i:prev){
////            System.out.println(i+" ");
////        }
//            
//        return prev;
//    }
////    int[] shortest = new int[100];
////    int numberMove;
//    
//    public static void main(String[] args) {
//        //without ladder / 
//        try {
//                 shortestWayToWin shortestMoves = new shortestWayToWin();            
//                //for required moves print
//                int[] snakes = snake.fixSnakes();
//                int[] ladders = ladder.fixLadders();            
//                int[] reqMove = new int[1001];
//                int countReMove = 0;
//                int[] prev = shortestMoves.bfs(snakes,ladders,1);
//                int cell = 100;
//                System.out.println("printing move: ");
//                while(prev[cell]!=1){
//                    System.out.print(cell+" ");
//                    reqMove[countReMove] = cell - prev[cell];
//                    cell = prev[cell];
//                    countReMove++;
//                }            
////                System.out.println(color.YELLOW+"\nMove suggestions: ");
////                for(int i=countReMove-1;i>=0;i--){
////                    System.out.print(reqMove[i]+" ");
////                }
//            } catch (Exception e) {
//                System.out.println("Error in bfs code"+e.getMessage());
//            }
//    }
//}
