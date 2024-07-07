package snake.ladders;
/**
 *
 * @author Noyon
 */
public class decryptPass {
    //pass protection       
    static int inverseKey[][] = new int[2][2];    
    static String realPass;
    static String decrypt(String pass){   
        realPass = "";
        divide(pass);
        return realPass;
    }
    static void divide(String pass){
//        System.out.print("Ascii of realPass:  ");
        int twoNumbers[] = new int[2];
        int j=0; 
        for(int i=0; i<18; i+=3){
            twoNumbers[j]= Integer.parseInt(pass.substring(i, (i+3)));
            j++;
            if(j%2==0)
            {
                j=0;
                decrypt(twoNumbers);
            }
        }
//        System.out.println("\n\n\nNEW real pass was: "+realPass);
    }

    static void decrypt(int twoNumbers[]){        
        int determinate;
        determinate = (encryptPass.key[0][0]*encryptPass.key[1][1])-(encryptPass.key[0][1]*encryptPass.key[1][0]);
        inverseKey[0][0] = encryptPass.key[1][1];
        inverseKey[1][1] = encryptPass.key[0][0];
        inverseKey[0][1] = -encryptPass.key[0][1];
        inverseKey[1][0] = -encryptPass.key[1][0];
        int sum;
        for(int i=0; i<2; i++){
            sum=0;
            for(int j=0; j<2; j++){
                sum += (twoNumbers[j]*inverseKey[j][i])/determinate;
            }
            realPass = realPass +  (char)(sum);
//            System.out.print("  "+sum);
        }
    }    
    public static void main(String[] args) {
        String a = "noyon";
        String b = a;
        System.out.println("391292399298199148");
    }
}
