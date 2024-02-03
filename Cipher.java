/* Name: <your name goes here>
 * CS3 project 1: Cipher, Spring 2024, updated 1/28/2024
 * Cipher class
 */

 import java.util.Scanner;
 import java.util.Arrays;


 public class Cipher {
     private static Scanner scanner = new Scanner(System.in);
     private char[] characters; // supported characters in specified order
     private String original;
     // TODO: Declare more private attributes here
 
     /* precondition: 0 <= offset < 60, 1 <= blockSize */
 
     /**
      * Cipher constructor
      * precondition: 0 <= offset < 60, 1 <= blockSize
      * @param offset offset to index in characters array
      * @param blockSize size of blocks to reverse
      */
     public Cipher(String input) {
         characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();
         this.original = input;
     }
 
 
 
     /**
      * The encipher method returns the result of enciphering the given clear text string 
      * @param s clear text string to encipher
      * @return enciphered string
      */
 
     public char[] offset(int displacement){ //change to private later
        //copy array
         char[] originalArray = this.original.toCharArray();
         char[] modified = new char[originalArray.length];
        
         //offset (correct)

         for (int i = 0; i < originalArray.length; i++){
             modified[i] = originalArray[(i+displacement)%(originalArray.length)];
         }

         return modified;
     }
 
     public char[] blockReverse(int blockSize){ //change to private later
        //copy array
        char[] originalArray = this.original.toCharArray();
        char[] modified = new char[originalArray.length];

        //offsest (unfinished)
     
         for(int i = 0; i < Math.ceil(originalArray.length/blockSize); i++){
             for(int j = Math.min(i*blockSize, originalArray.length); j < Math.min(((i+1)*blockSize), originalArray.length); j++){
                 System.out.println(j);
             }
         }
         
 
         return modified;
     }
 
     
 
     
     public char[] encipher() {
         char[] originalArray = this.original.toCharArray();
         char[] modified = new char[originalArray.length];
 
         // TODO: add code here to encipher arr in place
 
         
         return modified;
     }
 
     /**
      * The decipher method returns the result of deciphering the given enciphered string
      * @param enciphered enciphered string to decipher
      * @return clear text deciphered string
      */
     public char[] decipher() {
        char[] originalArray = this.original.toCharArray();
        char[] modified = new char[originalArray.length];
 
         // TODO: add code here to decipher arr in place

         return modified;
     }
 
     /**
      * The static crack method attempts to decipher a string that was enciphered with unknown offset and block size
      * @param enciphered
      * @return
      */
     public String crack()
     {
         System.out.println(this.original);
         String bestDeciphered = null;
 
         /* TODO: add code here to construct Cipher instances with all reasonable
          * offsets and block lengths, using each to decipher the string, giving each
          * a score based on punctuation/capitalization conventions, keeping track of
          * the best deciphered string and score.
          */
 
         return bestDeciphered;
     }
 
     public static void main(String[] args)
     {
         System.out.println("Welcome to Cipher! Please enter all text as a single line, substituting \\n for newline characters.");
         boolean done = false;
         String command;
         Cipher mainCipher = new Cipher("");
         while (!done) {
             while (true){
                 System.out.print("Would you like to encipher, decipher, crack, or quit? ");
                 command = scanner.nextLine();
                 if (command.equals("encipher")){
                     System.out.println("Enter your string");
                     String input = scanner.nextLine();
                     mainCipher.original = input;
                     mainCipher.encipher();
                 }
                 else if (command.equals("decipher")){
                     System.out.println("Enter your string");
                     String input = scanner.nextLine();
                     mainCipher.original = input;
                     mainCipher.decipher();
                 }
                 else if (command.equals("crack")){
                     System.out.println("Enter your string");
                     String input = scanner.nextLine();
                     mainCipher.original = input;
                     mainCipher.crack();
                     break;
                 }
                 else if (command.equals("offset")){
                    System.out.println("Enter your string");
                    String input = scanner.nextLine();
                    System.out.println("Enter your offset");
                    String offset = scanner.nextLine();
                    mainCipher.original = input;
                    System.out.println(mainCipher.offset(Integer.parseInt(offset)));
                    break;
                }
                else if (command.equals("blockReverse")){
                    System.out.println("Enter your string");
                    String input = scanner.nextLine();
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
 
