import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;


public class Cipher {
    // try using lambac
    private char[] characters; // supported characters in specified order 
    private String original; // make private later, and add a get/set function
    private AllWords allwords = new AllWords("All_Words.txt");

    public String getoriginal() {
        return original;
    }

    public void setoriginal(String new_string) {
        this.original = new_string;
    }

    public Cipher(String input) {
        characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();
        this.original = input;
    }

    private String offset(int displacement, String newString) { // change to private later

        if (displacement < 0) {
            displacement = displacement % 60 + 60;
        }
        // copy array
        //char[] stringArr = newString.toCharArray();
        final int len = newString.length();
        char[] modifiedArr = new char[len];

        for (int i = 0; i < len; i++) {
            modifiedArr[i] = newString.charAt((i + displacement) % (len));
            //modifiedArr[i] = stringArr[(i + displacement) % (stringArr.length)];
        }
        
        return new String(modifiedArr);
    }

    private String blockReverse(int blockSize, String newString) { // change method name to reverseBlock
        char[] stringArr = newString.toCharArray();
        char[] modifiedArr = new char[stringArr.length];

        for (int i = 0; i < Math.ceil((double) stringArr.length / blockSize); i++) {
            // Calculate the start and end indices for the current block
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, stringArr.length) - 1;
            // Reverse the characters in the current block
            for (int j = start; j <= end; j++) {
                modifiedArr[j] = stringArr[end - (j - start)];
            }
        }
        String result = new String(modifiedArr);
        return result;
    }

    public String cipher(int displacement, int blockSize, int order) {
        String modified = "";
        if (order != 0) {
            modified = offset(displacement, this.original);
            modified = blockReverse(blockSize, modified);
        } else {
            modified = blockReverse(blockSize, this.original);
            modified = offset(displacement, modified);
        }

        return modified;
    }

    private int matchDictionary(String[] stringArr) {
        int score = 0;
        for (String word : stringArr) {
            if (allwords.getStringHashSet().contains(word.toLowerCase())) {
                score++;
            }
        }
        return score;
    }

    /**
     * The crack method returns the optimal decipher of the original string
     * 
     * 
     * @return deciphered string
     */
    
    public Vector<String> crack() {
        try (var writer = new BufferedWriter(new FileWriter("all_crack.txt"))) {
            // Write an empty string to clear the file
            writer.write("");

            System.out.println("Successfully cleared the file.");
        } catch (IOException e) {
            System.err.println("Error clearing the file: "
            
                + e.getMessage());
        }
        Vector<String> bestDeciphered = new Vector<>();
        int bestScore = -1;
        int score;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < this.original.length(); j++) {
                for (int k = 1; k < this.original.length(); k++) {
                    try (var writer = new BufferedWriter(new FileWriter("all_crack.txt", true))) {
                        score = 0;
                        String testcase = cipher(j, k, i);
                        String[] testcaseArr = testcase.split(" ");
                        char upper = testcase.charAt(0);
                        char last = testcase.charAt(testcase.length()-1);
                        if (Character.isUpperCase(upper)) {
                            score += 3;
                            }

                        if(last == '.' || last == '?' || last == '!'){
                            score += 2;
                        }

                        score += matchDictionary(testcaseArr);
                        if (score >= bestScore) {
                            bestScore = score;
                            bestDeciphered.clear();
                            bestDeciphered.add(testcase);
                        } else if (score == bestScore){
                            bestDeciphered.add(testcase);
                        }
                        writer.write(testcase + " " + score);
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println("jerryl screwed something up: " + e.getMessage());
                    }
                } // end for reverseBlock combination
            }
        }
        return bestDeciphered;
    }
}
