import java.util.Scanner;
import java.util.Arrays;
public class Input {
    public static void main(String[] args)
     {
        
         System.out.println("Welcome to Cipher! Please enter all text as a single line, substituting \\n for newline characters.");
         boolean done = false;
         String command;
         Cipher mainCipher = new Cipher("");
         Scanner scanner = new Scanner(System.in); 

         while (!done) {
             System.out.println("Enter the enciphered/deciphered string:");
             String input = scanner.nextLine();
             int input = banner.nextLine();
             mainCipher.setoriginal(input);
             while (true){
                 System.out.print("Would you like to encipher, decipher, crack, or quit? ");
                 command = scanner.nextLine();
                 
                 if (command.equals("encipher")){
                    System.out.println("displacemnt int");
                     int a= banner.nextLine();
                     if (a < 0){
                         System.out.println("displacemnt + int");
                     int a= banner.nextLine();
                     }
                                         System.out.println("block size int");

                    int b=banner.nextLine();
                                         System.out.println("order true or false");
                    boolean c=banner.nextLine();
                    System.out.println(mainCipher.encipher(a, b, c));
                 }
                 else if (command.equals("decipher")){
                     System.out.println(mainCipher.decipher());
                 }
                 else if (command.equals("crack")){
                     mainCipher.crack();
                     break;
                 }
                 else if (command.equals("quit")){
                     done = true;
                     break;
                 }
             }
             /* TODO: Add code to check which command the user entered, prompt for
              * additional information as needed (offset, blocksize, text for encipher and decipher,
              * text for crack), call other methods to perform the selected operation, and print the result.
              * When the user enters quit as the command, just set variable done to true.
              * Since both clear and encrypted text may contain newline characters, allow users to enter the
              * two character sequence \n and replace those with actual newline characters before calling your
              * methods.  The testoutputs.txt file assumes you will leave newline characters alone when
              * printing output.
              */
 
         }
     }
}
