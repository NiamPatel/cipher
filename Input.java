import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        // provide instructions for input formatting.
        System.out.println("Welcome to Cipher! Please enter all text as a single line, substituting \\n for newline characters.");

        // whether the user wants to exit the program.
        boolean done = false;

        // Variable to store user commands.
        String command;

        // Create a Scanner object to read input from the command line.
        Scanner scanner = new Scanner(System.in);

        while (!done) {
            System.out.println("Enter your command (encipher, decipher, crack, or quit):");
            // Read the user's command.
            command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "encipher":
                case "decipher":
                    // Ask for offset and block size
                    System.out.println("Enter offset:");
                    int offset = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter block size:");
                    int blockSize = Integer.parseInt(scanner.nextLine());

                    // Initialize a Cipher object with user-defined values.
                    Cipher mainCipher = new Cipher(offset, blockSize);

                    if (command.equalsIgnoreCase("encipher")) {
                        System.out.println("Enter the string to encipher:");
                        String toEncipher = scanner.nextLine();
                        System.out.println("Enciphered string:");
                        System.out.println(mainCipher.encipher(toEncipher));
                    } else {
                        System.out.println("Enter the string to decipher:");
                        String toDecipher = scanner.nextLine();
                        System.out.println("Deciphered string:");
                        System.out.println(mainCipher.decipher(toDecipher));
                    }
                    break;
                case "crack":
                    // Since cracking doesn't require initial offset and block size, we use default or any values
                    System.out.println("Enter the string to crack:");
                    String toCrack = scanner.nextLine();
                    Cipher crackCipher = new Cipher(0, 1); // Using arbitrary initial values
                    System.out.println("Cracked string:");
                    System.out.println(crackCipher.crack(toCrack));
                    break;
                case "quit":
                    done = true;
                    System.out.println("Exiting Cipher...");
                    break;
                default:
                    System.out.println("Invalid command. Please enter encipher, decipher, crack, or quit.");
                    break;
            }
        }
        // Close the scanner to prevent resource leaks.
        scanner.close();
    }
}
