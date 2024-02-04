import java.util.Scanner;
import java.util.Arrays;
public class Input {
    public static void main(String[] args)
     {
        
         System.out.println("Welcome to Cipher! Please enter all text as a single line, substituting \\n for newline characters.");
         boolean done = false;
         String command;
         Cipher mainCipher = new Cipher("");
         Scanner scanner = new Scanner(System.in);  // Declare a local scanner for user input

         while (!done) {
             while (true){
                 System.out.print("Would you like to encipher, decipher, crack, or quit? ");
                 if true{
                 System.out.println("Enter the fucking string");
                     String input = scanner.nextLine();
                     mainCipher.setoriginal = input;
                 command = scanner.nextLine();
                 }
                 if (command.equals("encipher")){
                     mainCipher.encipher();
                 }
                 else if (command.equals("decipher")){
                     mainCipher.decipher();
                 }
                 else if (command.equals("crack")){
                     mainCipher.crack();
                     break;
                 }
                 else if (command.equals("offset")){
                    System.out.println("Enter your offset");
                    String offset = scanner.nextLine();
                    System.out.println(mainCipher.offset(Integer.parseInt(offset)));
                    break;
                }
                else if (command.equals("blockReverse")){
                    System.out.println("Enter your blockSize");
                    String blockSize = scanner.nextLine();
                    mainCipher.original = input;
                    System.out.println(mainCipher.blockReverse(Integer.parseInt(blockSize)));
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
