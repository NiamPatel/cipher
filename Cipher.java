import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Arrays;
import java.util.HashMap;

public class Cipher {
    private HashMap<Character, Integer> charHashMap = new HashMap<>();
    private char[] characters;
    private AllWords allwords = new AllWords("All_Words.txt");
    private int offset;
    private int blockSize;
    private char[] curStr;
    private char[] originalStr; 

    public Cipher(int offset, int blockSize) {
        this.offset = offset;
        this.blockSize = blockSize;
        this.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \n,;:.!?".toCharArray();
        this.charHashMap = new HashMap<>();
        for (int i = 0; i < characters.length; i++) {
            charHashMap.put(characters[i], i);
        }
        this.allwords = new AllWords("All_Words.txt");
    }

    private void offset(int displacement) {
        if (displacement < 0) {
            displacement = displacement % 60 + 60;
        }
        final int len = curStr.length;
        

        for (int i = 0; i < len; i++) {
            Integer charIndex = charHashMap.get(curStr[i]);
            if (charIndex != null){
                curStr[i] = characters[((displacement+charIndex)%60 + 60)%60];
            } else {
                curStr[i] = '$';
            }
        }
    }

    private void blockReverse(int blockSize) {
        for (int i = 0; i < Math.ceil((double) curStr.length / blockSize); i++) {
            int start = i * blockSize;
            int end = Math.min((i + 1) * blockSize, curStr.length) - 1;
            int l = start;
            int r = end;
            while (l < r){
                char temp = curStr[l];
                curStr[l] = curStr[r];
                curStr[r] = temp;
                l++;
                r--;

            }
        }
    }

    private String cipher(int order) {
        if (order != 0) {
            offset(-offset);
            blockReverse(blockSize);
        } else {
            blockReverse(blockSize);
            offset(offset);
        }
        return new String(curStr);
    }

    public String encipher(String original) {
        curStr = original.toCharArray();
        originalStr = original.toCharArray();
        return cipher(0);
    }

    public String decipher(String original) {
        curStr = original.toCharArray();
        originalStr = original.toCharArray();
        return cipher(1);
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

    public static String crack(String original) {
        Cipher cipher = new Cipher(0, 0);
        cipher.curStr = original.toCharArray();
        cipher.originalStr = original.toCharArray();
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
                        score = 0;
                        cipher.curStr = Arrays.copyOf(cipher.originalStr, cipher.originalStr.length);
                        score = 0;
                        cipher.offset = j;
                        cipher.blockSize = k;

                        
                        String testcase = cipher.cipher(1);
                        String[] testcaseArr = testcase.split(" ");
                        char upper = testcase.charAt(0);
                        char last = testcase.charAt(testcase.length()-1);

                        if (Character.isUpperCase(upper)) {
                            score++;
                        }

                        if(last == '.' || last == '?' || last == '!'){
                            score += 3;
                        }

                        score += cipher.matchDictionary(testcaseArr);
                        if (score >= bestScore) {
                            bestScore = score;
                            bestDeciphered.clear();
                            bestDeciphered.add(testcase);
                        } else if (score == bestScore){
                            bestDeciphered.add(testcase);
                        }
                }
            }
        }
        return bestDeciphered.get(0);
    }
}
