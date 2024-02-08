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
                if (command.equalsIgnoreCase("cipher")) {
                    boolean validInput = false;
                    int displacement = 0, blockSize = 0, order = 0;
                    while (!validInput) {
                        System.out.println("Input displacement(int), blockSize(int), order(int):");
                        String parameters = scanner.nextLine();
                        String[] parameterArr = parameters.split(" ");
                        try {
                            displacement = Integer.parseInt(parameterArr[0]); // has to be int
                            blockSize = Integer.parseInt(parameterArr[1]);    // has to be int, between 1 and string length
                            order = Integer.parseInt(parameterArr[2]);        // has to be int
                            
                            // Check for negative values and blockSize within the range
                            if (blockSize < 1 || blockSize > input.length()) {
                                System.out.println("Please ensure displacement and order are non-negative and blockSize is between 1 and the length of the input string.");
                            } else {
                                validInput = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please ensure you enter integers only.");
                        }
                    }
                    System.out.println(mainCipher.cipher(displacement, blockSize, order));
                    break;
                } else if (command.equalsIgnoreCase("crack")) {
                    System.out.println(mainCipher.crack());

                    break;
                } else if (command.equalsIgnoreCase("quit")) {
                    done = true;
                    break;
                }
            }
        }
        scanner.close();
    }
}

