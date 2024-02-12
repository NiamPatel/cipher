import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * The Cipher class implements methods for encrypting and decrypting strings using a simple cipher mechanism,
 * as well as attempting to crack the cipher through brute force.
 */
public class Cipher {
    // Array of characters that are allowed in the cipher text.
    private char[] characters; 
    // The original text that will be encrypted or decrypted.
    private String original; 
    // Reference to an AllWords object which loads a dictionary from a file for the purpose of cracking the cipher.
    private AllWords allwords = new AllWords("All_Words.txt");

    /**
     * Constructor initializes a new instance of the Cipher class with a specific input string.
     *
     * @param input The string to be encrypted or decrypted.
     */
   public String encipher(int displacement, int blockSize) {
        return cipher(displacement, blockSize, 0);
    }

    public String decipher(int displacement, int blockSize) {
        return cipher(displacement, blockSize, 1);
    }
    private Cipher(String input) {
        // Initialize the characters array with a predefined set of characters that includes
        // uppercase and lowercase letters, spaces, new lines, and some punctuation marks.
        characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();
        // Set the original string to the input provided.
        this.original = input;
    }

    /**
     * Getter for the original string.
     *
     * @return The original text.
     */
    public String getOriginal() {
        return original;
    }

    /**
     * Setter for the original string. Allows updating the original text.
     *
     * @param newString The new text to set as the original.
     */
    public void setOriginal(String newString) {
        this.original = newString;
    }

    /**
     * Encodes or decodes the original string by applying an offset and block reversal based on the parameters.
     * The order of these operations can be specified.
     *
     * @param displacement The number of positions each character in the string is to be shifted.
     * @param blockSize The size of the blocks to be reversed.
     * @param order Specifies the order of operations: 0 for block reversal first, 1 for offset first.
     * @return The modified string after encoding or decoding.
     */
    public String cipher(int displacement, int blockSize, int order) {
        String modified = "";
        // Depending on the order, apply offset and then block reverse, or vice versa.
        if (order != 0) {
            modified = offset(displacement, this.original);
            modified = blockReverse(blockSize, modified);
        } else {
            modified = blockReverse(blockSize, this.original);
            modified = offset(displacement, modified);
        }

        return modified;
    }

    /**
     * Applies a displacement offset to each character in the input string.
     *
     * @param displacement The number of positions to shift each character.
     * @param newString The string to be modified.
     * @return A new string with each character shifted by the specified displacement.
     */
    private String offset(int displacement, String newString) {
        // Adjust negative displacement to ensure it wraps around correctly.
        if (displacement < 0) {
            displacement = displacement % 60 + 60;
        }
        final int len = newString.length();
        char[] modifiedArr = new char[len];

        // Shift each character in the string by the displacement value.
        for (int i = 0; i < len; i++) {
            modifiedArr[i] = newString.charAt((i + displacement) % (len));
        }

        return new String(modifiedArr);
    }

    /**
     * Reverses characters within blocks of the specified size in the input string.
     *
     * @param blockSize The size of each block that is to be reversed.
     * @param newString The string to be modified.
     * @return A new string with blocks of characters reversed.
     */
    private String blockReverse(int blockSize, String newString) {
        char[] stringArr = newString.toCharArray();
        char[] modifiedArr = new char[stringArr.length];

        // Iterate over the string in blocks and reverse each block.
        for (int i = 0; i < Math.ceil((double) stringArr.length / blockSize); i++) {
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, stringArr.length) - 1;
            for (int j = start; j <= end; j++) {
                modifiedArr[j] = stringArr[end - (j - start)];
            }
        }
        return new String(modifiedArr);
    }

    /**
     * Compares each word in the input array with a dictionary and counts matches.
     *
     * @param stringArr An array of words to be checked against the dictionary.
     * @return The number of words in the input array that match words in the dictionary.
     */
    private int matchDictionary(String[] stringArr) {
        int score = 0;
        // Increment score for each word found in the dictionary.
        for (String word : stringArr) {
            if (allwords.getStringHashSet().contains(word.toLowerCase())) {
                score++;
            }
        }
        return score;
    }

    /**
     * Attempts to crack the cipher by trying various combinations of displacements and block sizes,
     * scoring each attempt based on dictionary matches and certain heuristics.
     *
     * @return A Vector of the best deciphered strings based on the scoring mechanism.
     */
    public Vector<String> crack() {
        // Clear the file at the beginning of the crack attempt.
        try (var writer = new BufferedWriter(new FileWriter("all_crack.txt"))) {
            writer.write("");
            System.out.println("Successfully cleared the file.");
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }

        Vector<String> bestDeciphered = new Vector<>();
        int bestScore = -1; // Initialize with a score that can be easily surpassed.
        int score;
        // Nested loops to try various combinations of displacement, block size, and operation order.
        for (int i = 0; i <= 1; i++) { // Loop over the two possible orders of operations.
            for (int j = 0; j < this.original.length(); j++) { // Loop over possible displacements.
                for (int k = 1; k < this.original.length(); k++) { // Loop over possible block sizes.
                    try (var writer = new BufferedWriter(new FileWriter("all_crack.txt", true))) {
                        score = 0;
                        String testcase = cipher(j, k, i);
                        String[] testcaseArr = testcase.split(" ");
                        char upper = testcase.charAt(0);
                        char last = testcase.charAt(testcase.length() - 1);
                        // Add to score based on the capitalization of the first letter and punctuation at the end.
                        if (Character.isUpperCase(upper)) {
                            score += 3;
                        }
                        if (last == '.' || last == '?' || last == '!') {//if puncation then it prob better
                            score += 2;
                        }
                        // Further increase the score based on dictionary matches.
                        score += matchDictionary(testcaseArr);
                        // Update the best score and best deciphered text if the current score is higher.
                        if (score > bestScore) {
                            bestScore = score;
                            bestDeciphered.clear();
                            bestDeciphered.add(testcase);
                        } else if (score == bestScore) {
                            // If the current score equals the best score, add the current text to the list of best texts.
                            bestDeciphered.add(testcase);
                        }
                        // Write each attempt and its score to a file for analysis.
                        writer.write(testcase + " " + score);
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
            }
        }
        // Return the vector containing the best deciphered strings.
        return bestDeciphered;
    }
}
