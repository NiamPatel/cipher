/* Name: <your name goes here>
 * CS3 project 1: Cipher, Spring 2024, updated 1/28/2024
 * Cipher class
 */

 import java.util.Scanner;
 import java.util.Arrays;


 public class Cipher {
     public String getoriginal()
     {
     return original;
     }
     public void setoriginal(String new_string)
     {
     this.original = new_string;
     }
     private static Scanner scanner = new Scanner(System.in);
     private char[] characters; // supported characters in specified order
     private String original; //make private later, and add a get/set function
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
 
     public char[] blockReverse(int blockSize) {
        // Copy array
        char[] originalArray = this.original.toCharArray();
        char[] modified = new char[originalArray.length];
    
        for (int i = 0; i < Math.ceil(originalArray.length / blockSize); i++) {
            // Calculate the start and end indices for the current block
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, originalArray.length) - 1;
    
            // Reverse the characters in the current block
            for (int j = start; j <= end; j++) {
                modified[j] = originalArray[end - (j - start)];
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
 
     
 }
 
