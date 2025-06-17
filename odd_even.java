import java.util.Scanner;

public class odd_even{
    public static void main(String[] args) {
        // Creating Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Taking user input for limit
        System.out.print("Enter the limit: ");
        int limit = scanner.nextInt();

        int choice;

        // Loop until user selects option 3 (Exit)
        while (true) {
            // Display menu options
            System.out.println("\nChoose an option:");
            System.out.println("1 - Print Even Numbers");
            System.out.println("2 - Print Odd Numbers");
            System.out.println("3 - Exit");

            choice = scanner.nextInt(); // Read user choice
            // Using switch-case based on user choice
            switch (choice) {
                case 1:
                    System.out.println("Even numbers up to " + limit + ":");
                    for (int i = 1; i <= limit; i++) {
                        if (i % 2 == 0) {
                            System.out.print(i + " ");
                        }
                    }
                    System.out.println(); // New line for better formatting
                    break;

                case 2:
                    System.out.println("Odd numbers up to " + limit + ":");
                    for (int i = 1; i <= limit; i++) {
                        if (i % 2 != 0) {
                            System.out.print(i + " ");
                        }
                    }
                    System.out.println(); // New line for better formatting
                    break;

                case 3:
                    System.exit(0); // Terminates the program
                    scanner.close();
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1, 2, or 3.");
            }
        }


    }

}