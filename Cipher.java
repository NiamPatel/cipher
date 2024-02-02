/* Name: <your name goes here>
 * CS3 project 1: Cipher, Spring 2024, updated 1/28/2024
 * Cipher class
 */

import java.util.Scanner;

public class Node { 
    char s; 
    Node prev; 
    Node next; 
  
    public Node(char s) 
    { 
        this.s = s; 
        this.prev = null; 
        this.next = null; 
    } 
} 

public class Cipher {
    private static Scanner scanner = new Scanner(System.in);
    private char[] characters; // supported characters in specified order
    // TODO: Declare more private attributes here

    /* precondition: 0 <= offset < 60, 1 <= blockSize */

    /**
     * Cipher constructor
     * precondition: 0 <= offset < 60, 1 <= blockSize
     * @param offset offset to index in characters array
     * @param blockSize size of blocks to reverse
     */
    public Cipher(int offset, int blockSize) {
        characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();

        // TODO: initialize other attributes here
    }

    // TODO: add private helper methods here

    /**
     * The encipher method returns the result of enciphering the given clear text string 
     * @param s clear text string to encipher
     * @return enciphered string
     */

    private String offset(String enciphered, int n){
        String new = ""*enciphered.length();
        for(int i = n, i < n + enciphered.length(); i++) {
            
        }
        
    }
    public String encipher(String clear) {
        char[] arr = clear.toCharArray();

        // TODO: add code here to encipher arr in place

        String result = new String(arr);
        return result;
    }

    /**
     * The decipher method returns the result of deciphering the given enciphered string
     * @param enciphered enciphered string to decipher
     * @return clear text deciphered string
     */
    public String decipher(String enciphered) {
        char[] arr = enciphered.toCharArray();

        // TODO: add code here to decipher arr in place

        String result = new String(arr);
        return result;
    }

    /**
     * The static crack method attempts to decipher a string that was enciphered with unknown offset and block size
     * @param enciphered
     * @return
     */
    public static String crack(String enciphered)
    {
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
        while (!done) {
            while (true){
                System.out.print("Would you like to encipher, decipher, crack, or quit? ");
                command = scanner.nextLine();
                if (command.equals("encipher")){
                    break;
                }
                else if (command.equals("decipher")){
                    break;
                }
                else if (command.equals("crack")){
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
