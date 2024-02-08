import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        // Greet the user and provide instructions for input formatting.
        System.out.println("Welcome to Cipher! Please enter all text as a single line, substituting \\n for newline characters.");
        
        // Flag to keep track of whether the user wants to exit the program.
        boolean done = false;
        
        // Variable to store user commands.
        String command;
        
        // Initialize a Cipher object with an empty string to start.
        Cipher mainCipher = new Cipher("");
        
        // Create a Scanner object to read input from the command line.
        Scanner scanner = new Scanner(System.in);

        // Main loop to keep the program running until the user decides to quit.
        while (!done) {
            System.out.println("Enter the enciphered/deciphered string:");
            // Read the string that the user wants to encipher or decipher.
            String input = scanner.nextLine();
            
            // There's a typo here; it should match the method name in the Cipher class.
            mainCipher.setOriginal(input); // Corrected from `setoriginal` to match the method name `setOriginal`.
            
            // Inner loop for processing commands on the entered string.
            while (true) {
                System.out.print("Would you like to (en/de)cipher, crack, or quit? ");
                // Read the user's command.
                command = scanner.nextLine();
                
                // Process the command to encipher or decipher the string.
                if (command.equalsIgnoreCase("cipher")) {
                    // Flag to check for valid input.
                    boolean validInput = false;
                    // Variables to store cipher parameters.
                    int displacement = 0, blockSize = 0, order = 0;
                    while (!validInput) {
                        System.out.println("Input displacement(int), blockSize(int), order(int):");
                        // Read the cipher parameters from the user.
                        String parameters = scanner.nextLine();
                        String[] parameterArr = parameters.split(" ");
                        try {
                            // Parse the parameters from the input string.
                            displacement = Integer.parseInt(parameterArr[0]);
                            blockSize = Integer.parseInt(parameterArr[1]);
                            order = Integer.parseInt(parameterArr[2]);
                            
                            // Validate blockSize to ensure it's within the correct range.
                            if (blockSize < 1 || blockSize > input.length()) {
                                System.out.println("Please ensure displacement and order are non-negative and blockSize is between 1 and the length of the input string.");
                            } else {
                                // If the input is valid, exit the loop.
                                validInput = true;
                            }
                        } catch (NumberFormatException e) {
                            // Catch and handle incorrect input format.
                            System.out.println("Please ensure you enter integers only.");
                        }
                    }
                    // Output the result of the cipher operation.
                    System.out.println(mainCipher.cipher(displacement, blockSize, order));
                    break; // Exit the inner loop after processing.
                } 
                else if (command.equalsIgnoreCase("crack")) {
                    // Attempt to crack the cipher and print the result.
                    System.out.println(mainCipher.crack());
                    break; // Exit the inner loop after attempting to crack.
                } 
                else if (command.equalsIgnoreCase("quit")) {
                    // Set the flag to exit the main loop and end the program.
                    done = true;
                    break; // Exit the inner loop.
                }
            }
        }
        // Close the scanner to prevent resource leaks.
        scanner.close();
    }
}
