import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Arrays;
import java.util.HashMap;

public class Cipher {
    private static HashMap<Character, Integer> charHashMap = new HashMap<>();
    private static char[] characters;
    private static AllWords allwords = new AllWords("All_Words.txt");
    private static int offset;
    private static int blockSize;

    public Cipher(int offset, int blockSize) {
        this.offset = offset;
        this.blockSize = blockSize;
        this.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();
        for (int i = 0; i < characters.length; i++) {
            charHashMap.put(characters[i], i);
        }
    }

    private static String offset(int displacement, String newString) {
        if (displacement < 0) {
            displacement = displacement % 60 + 60;
        }
        final int len = newString.length();
        char[] modifiedArr = new char[len];

        for (int i = 0; i < len; i++) {
            Integer charIndex = charHashMap.get(newString.charAt(i));
            if (charIndex != null){
                modifiedArr[i] = characters[((displacement+charIndex)%60 + 60)%60];
            } else {
                modifiedArr[i] = '$';
            }
        }
        return new String(modifiedArr);
    }

    private static String blockReverse(int blockSize, String newString) {
        char[] stringArr = newString.toCharArray();
        char[] modifiedArr = new char[stringArr.length];

        for (int i = 0; i < Math.ceil((double) stringArr.length / blockSize); i++) {
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, stringArr.length) - 1;
            for (int j = start; j <= end; j++) {
                modifiedArr[j] = stringArr[end - (j - start)];
            }
        }
        return new String(modifiedArr);
    }

    private static String cipher(String original, int order) {
        String modified = "";
        if (order != 0) {
            modified = offset(offset, original);
            modified = blockReverse(blockSize, modified);
        } else {
            modified = blockReverse(blockSize, original);
            modified = offset(offset, modified);
        }
        return modified;
    }

    public static String encipher(String original) {
        return cipher(original, 0);
    }

    public static String decipher(String original) {
        Cipher.offset = -offset;
        String modified = cipher(original, 1);
        Cipher.offset = -offset;
        return modified;
    }

    private static int matchDictionary(String[] stringArr) {
        int score = 0;
        for (String word : stringArr) {
            if (allwords.getStringHashSet().contains(word.toLowerCase())) {
                score++;
            }
        }
        return score;
    }

    public static String crack(String original) {
        try (var writer = new BufferedWriter(new FileWriter("all_crack.txt"))) {
            writer.write("");
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }

        Vector<String> bestDeciphered = new Vector<>();
        int bestScore = -1;
        int score;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < original.length(); j++) {
                for (int k = 1; k < original.length(); k++) {
                    try (var writer = new BufferedWriter(new FileWriter("all_crack.txt", true))) {
                        score = 0;
                        Cipher.offset = j;
                        Cipher.blockSize = k;
                        String testcase = decipher(original);
                        String[] testcaseArr = testcase.split(" ");
                        char upper = testcase.charAt(0);
                        char last = testcase.charAt(testcase.length()-1);
                        if (Character.isUpperCase(upper)) {
                            score++;
                        }

                        if(last == '.' || last == '?' || last == '!'){
                            score += 3;
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
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                }
            }
        }
        return bestDeciphered.get(0);
    }
}
