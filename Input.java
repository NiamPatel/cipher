import java.util.Scanner;

public class Input {
    public static void main(String[] args) {

        System.out.println("Welcome to Cipher! Please enter all text as a single line, substituting \\n for newline characters.");
        boolean done = false;
        String command;
        Cipher mainCipher = new Cipher("");
        Scanner scanner = new Scanner(System.in);

        while (!done) {
            System.out.println("Enter the enciphered/deciphered string:");
            String input = scanner.nextLine();
            mainCipher.setoriginal(input);
            while (true) {
                System.out.print("Would you like to (en/de)cipher, crack, or quit? ");
                command = scanner.nextLine();

                if (command.equals("cipher")) {
                    System.out.println("Input displacement(int), blockSize(int), order(int)");
                    String parameters = scanner.nextLine();
                    String[] parameterArr = parameters.split(" ");

                    try {
                        int displacement = Integer.parseInt(parameterArr[0]); // has to be int
                        int blockSize = Integer.parseInt(parameterArr[1]);    // has to be int, between 1 and string length
                        int order = Integer.parseInt(parameterArr[2]);        // has to be int

                        // Check if blockSize is between 1 and the length of the input string
                        if (blockSize < 1 || blockSize > input.length()) {
                            System.out.println("blockSize must be between 1 and the length of the input string.");
                            continue; // Prompt the user again for correct values
                        }

                        // If all conditions are met, proceed to cipher
                        System.out.println(mainCipher.cipher(displacement, blockSize, order));
                    } catch (NumberFormatException e) {
                        System.out.println("Please ensure displacement, blockSize, and order are integers.");
                        continue; // Prompt the user again for correct values
                    }
                    break;
                } else if (command.equals("crack")) {
                    mainCipher.crack();
                    break;
                } else if (command.equals("quit")) {
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
             * methods. The testoutputs.txt file assumes you will leave newline characters alone when
             * printing output.
             */
        }
    }
}
