package snake.ladders;
/**
 *
 * @author Noyon
 */
public class player {
        int id, player, tempPosition, position, totalPoints, moveLeft;    
        String playerName;
        char icon;
        int diceNum[] = new int[3];
        
    static Boolean PlayerProfile(player[] objPlayer,int playerNumber){
        SnakeLadders.printTitle();
        while(true){
            System.out.println("For player: "+playerNumber);
            System.out.println("\n\t1. Sign In");        
            System.out.println("\t2. Sign Up");            
            System.out.println("\t0. Cancel Game");            
            System.out.print(color.RED+"\n\tChoose option: "+color.RESET);
            String opt = SnakeLadders.in.nextLine();
            switch(opt){
                case "0" -> {
                    return false;
                }
                case "1" -> {
                    login log = new login();
                    return log.account(objPlayer, playerNumber);
                }
                case "2" -> {
                    createAccount create = new createAccount();
                    return create.account(objPlayer, playerNumber);
                }
                default -> {
                    System.out.println("Option doesn't exist..\n\n\n");
                }
            }
        }
    }
}
