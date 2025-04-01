import java.util.Scanner;

/**
 * Displays the customer menu and handles customer actions.
 */
public class CustomerMenu {
    public void showCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. View All Movies");
            System.out.println("2. View All Showtimes");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Order History");
            System.out.println("5. View Concessions");
            System.out.println("6. Purchase Concessions");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // viewAllMovies();
                    break;
                case 2:
                    // viewAllShowtimes();
                    break;
                case 3:
                    // bookTicket();
                    break;
                case 4:
                    // viewOrderHistory();
                    break;
                case 5:
                    // viewConcessions();
                    break;
                case 6:
                    // purchaseConcessions();
                    break;
                case 7:
                    running = false; // Log out and return to the login menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}