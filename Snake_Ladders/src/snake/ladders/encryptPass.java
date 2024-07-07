package snake.ladders;
/**
 *
 * @author Noyon
 */
public class encryptPass {
    //pass protection
    static final int key[][]= {{1,2},{3,1}};
    static String hiddenPass;
    
    static String encrypt(String pass){   
        hiddenPass = "";
        divide(pass);
        return hiddenPass;
    }
    static void divide(String pass){     
        int twoChars[] = new int[2];
        int j=0;
        for(int i=0; i<6 ; i++){
            twoChars[j] = (int)(pass.charAt(i));
            j++;
            if(j%2==0){
                j=0;
                encrypt(twoChars);
            }
        }
//        System.out.println("\n\nEncrypted pass: "+hiddenPass);
    }

    static void encrypt(int twoChars[]){        
        int sum;
        for(int i=0; i<2; i++){
            sum=0;
            for(int j=0; j<2; j++){
                sum += twoChars[j]*key[j][i];
            }
            if(String.valueOf(sum).length()<3)
                hiddenPass = hiddenPass + '0'+ String.valueOf(sum);
            else            
                hiddenPass = hiddenPass +  String.valueOf(sum);
//            System.out.print("  "+sum);
        }
    }
    public static void main(String[] args) {
        encrypt("  !   ");        
        decryptPass.decrypt(hiddenPass);
        encrypt("123456"); 
        decryptPass.decrypt(hiddenPass);
        encrypt("~v~a~z");
        decryptPass.decrypt(hiddenPass);
    }
}
