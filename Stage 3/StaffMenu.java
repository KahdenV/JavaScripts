import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Displays the staff menu and handles staff actions.
 */
public class StaffMenu {
    private List<Staff> staff;
    private List<Customer> customers;
    private List<Movie> movies;
    private Map<String, Concession> concessions;

    // Updated constructor to match the call in MovieTheaterSystem
    public StaffMenu(List<Staff> staff, List<Customer> customers, List<Movie> movies, Map<String, Concession> concessions) {
        this.staff = staff;
        this.customers = customers;
        this.movies = movies;
        this.concessions = concessions;
    }

    public void showStaffMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Staff Menu ===");
            System.out.println("1. View All Staff");
            System.out.println("2. Add New Staff");
            System.out.println("3. View All Customers");
            System.out.println("4. View Inventory");
            System.out.println("5. Add Stock");
            System.out.println("6. Update Stock");
            System.out.println("7. Add Movie");
            System.out.println("8. Update Movie");
            System.out.println("9. Manage Refunds");
            System.out.println("10. View Payments");
            System.out.println("11. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllStaff();
                    break;
                case 2:
                    addNewStaff(scanner);
                    break;
                case 3:
                    viewAllCustomers();
                    break;
                case 4:
                    viewInventory();
                    break;
                case 5:
                    addStock(scanner);
                    break;
                case 6:
                    updateStock(scanner);
                    break;
                case 7:
                    addMovie(scanner);
                    break;
                case 8:
                    updateMovie(scanner);
                    break;
                case 9:
                    manageRefunds(scanner);
                    break;
                case 10:
                    viewPayments();
                    break;
                case 11:
                    running = false; // Log out and return to the login menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays all concession items as inventory.
     */
    private void viewInventory() {
        System.out.println("\n=== Inventory ===");
        Map<String, Concession> concessions = Concession.getConcessionMenu(); // Always get the latest map
        if (concessions.isEmpty()) {
            System.out.println("No concession items available.");
        } else {
            for (Concession item : concessions.values()) {
                System.out.println(item.getConcessionId() + ": " + item.getItemName() + " - $" + item.getPrice());
            }
        }
    }

    /**
     * Adds a new concession item to the inventory.
     *
     * @param scanner The Scanner object for user input.
     */
    private void addStock(Scanner scanner) {
        System.out.println("\n=== Add Stock ===");
        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Concession.addItem(itemId, itemName, price);
    }

    /**
     * Updates an existing concession item in the inventory.
     *
     * @param scanner The Scanner object for user input.
     */
    private void updateStock(Scanner scanner) {
        System.out.println("\n=== Update Stock ===");
        System.out.print("Enter item ID: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter new item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter new item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Concession.updateItem(itemId, itemName, price);
    }

    /**
     * Adds a new movie to the list.
     *
     * @param scanner The Scanner object for user input.
     */
    private void addMovie(Scanner scanner) {
        System.out.println("\n=== Add New Movie ===");
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter movie genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter movie runtime (in minutes): ");
        int runtime = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Movie newMovie = new Movie(title, genre, runtime);
        movies.add(newMovie);
        System.out.println("New movie added successfully.");
    }

    /**
     * Adds a new staff member.
     *
     * @param scanner The Scanner object for user input.
     */
    private void addNewStaff(Scanner scanner) {
        System.out.println("\n=== Add New Staff ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Staff newStaff = new Staff(name, username, password);
        staff.add(newStaff);

        System.out.println("New staff member added successfully.");
    }

    /**
     * Displays all staff members.
     */
    private void viewAllStaff() {
        System.out.println("\n=== View All Staff ===");
        for (Staff s : staff) {
            System.out.println(s.getName() + " - " + s.getEmail());
        }
    }

    /**
     * Displays all customers with their IDs and names.
     */
    private void viewAllCustomers() {
        System.out.println("\n=== View All Customers ===");
        for (Customer c : customers) {
            System.out.println(c.getCustomerId() + " - " + c.getName() + " (" + c.getEmail() + ")");
        }
    }

    /**
     * Updates an existing movie.
     *
     * @param scanner The Scanner object for user input.
     */
    private void updateMovie(Scanner scanner) {
        System.out.println("\n=== Update Movie ===");
        System.out.print("Enter the movie title to update: ");
        String title = scanner.nextLine();

        for (Movie movie : movies) {
            if (movie.getMovieTitle().equalsIgnoreCase(title)) {
                System.out.println("Movie found. Updating details...");
                // Movie update logic here
                return;
            }
        }
        System.out.println("Movie not found.");
    }

    /**
     * Manages staff-related actions, including refunds.
     */
    private void manageRefunds(Scanner scanner) {
        System.out.println("\n=== Manage Refunds ===");
        System.out.print("Enter Payment ID to refund: ");
        String paymentId = scanner.nextLine();

        Payment.refundPayment(paymentId);
    }

    /**
     * Displays all payments.
     */
    private void viewPayments() {
        System.out.println("\n=== View Payments ===");
        if (Payment.getPaymentRecords().isEmpty()) {
            System.out.println("No payments have been processed.");
        } else {
            for (Payment payment : Payment.getPaymentRecords().values()) {
                System.out.println(payment); // Assumes the Payment class has a meaningful toString() method
            }
        }
    }
}