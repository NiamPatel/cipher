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
 
     public String offset(int displacement, String new_string){ //change to private later
        if (displacement < 0){
            displacement = displacement%60 + 60;
        }
        //copy array
         char[] stringArr = new_string.toCharArray();
         char[] modifiedArr = new char[stringArr.length];

         for (int i = 0; i < stringArr.length; i++){
             modifiedArr[i] = stringArr[(i+displacement)%(stringArr.length)];
         }

         String result = new String(modifiedArr);
         return result;
     }
 
     public String blockReverse(int blockSize, String new_string) {
        
        char[] stringArr = new_string.toCharArray();
        char[] modifiedArr = new char[stringArr.length];
        
        
    
        for (int i = 0; i < Math.ceil((double)stringArr.length / blockSize); i++) {
            // Calculate the start and end indices for the current block
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, stringArr.length) - 1;
            System.out.println(start);
            System.out.println(end);
            // Reverse the characters in the current block
            for (int j = start; j <= end; j++) {
                modifiedArr[j] = stringArr[end - (j - start)];
            }
        }
        String result = new String(modifiedArr);
        return result;
    }
    
     public String cipher(int displacement, int blockSize, Boolean order) {
         String modified = "";
         if (order){
            modified = offset(displacement, this.original);
            modified = blockReverse(blockSize, modified);
         } else {
            modified = blockReverse(blockSize, modified);
            modified = offset(displacement, modified);
         }
 
         
         return modified;
     }

 
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
 
