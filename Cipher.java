
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Cipher {
    private char[] characters;
    private String original;
    private AllWords allwords = new AllWords("All_Words.txt");

    public Cipher(String input) {
        characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();
        this.original = input;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String newString) {
        this.original = newString;
    }

    public String cipher(int displacement, int blockSize, int order) {
        String modified = original;
        if (order == 0) {
            modified = blockReverse(blockSize, modified);
            modified = offset(displacement, modified);
        } else {
            modified = offset(displacement, modified);
            modified = blockReverse(blockSize, modified);
        }
        return modified;
    }

    public String encipher(int displacement, int blockSize) {
        return cipher(displacement, blockSize, 1);
    }

    public String decipher(int displacement, int blockSize) {
        return cipher(-displacement, blockSize, 0);
    }

    private String offset(int displacement, String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            int index = new String(characters).indexOf(c);
            if (index != -1) {
                index = (index + displacement + characters.length) % characters.length;
                result.append(characters[index]);
            } else {
                result.append(c); // Keep characters not found in the array as is.
            }
        }
        return result.toString();
    }

    private String blockReverse(int blockSize, String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i += blockSize) {
            int end = Math.min(i + blockSize, str.length());
            String block = new StringBuilder(str.substring(i, end)).reverse().toString();
            result.append(block);
        }
        return result.toString();
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
