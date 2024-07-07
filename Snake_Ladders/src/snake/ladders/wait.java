/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package snake.ladders;

/**
 *
 * @author User
 */
public class wait {
    static void time(int n){
        for(int i = 1 ; i < n ; i++){
            System.out.println(color.BOLD+"Updating in : " + (n-i) + "s"+color.UNBOLD); 
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){    
                System.out.println(e.getMessage());
            }
        }
    }
}
