import java.util.Scanner;

/**
 * Manages the application lifecycle, including initialization, shutdown, and user authentication.
 */
public class ApplicationManager {
    private MenuManager menuManager;
    private AuthenticationService authService;
    private int guestCounter = 1; // Counter to track guest IDs

    public ApplicationManager(MenuManager menuManager, AuthenticationService authService) {
        this.menuManager = menuManager;
        this.authService = authService;
    }

    /**
     * Initializes system components.
     */
    public void initialize() {
        System.out.println("System initializing...");
    }

    /**
     * Shuts down system components.
     */
    public void shutdown() {
        System.out.println("System shutting down...");
    }

    /**
     * Starts the application and handles user authentication.
     */
    public void start() {
        while (true) {
            Person authenticatedUser = authenticateUser();
            if (authenticatedUser != null) {
                menuManager.displayMainMenu(authenticatedUser);
            } else {
                System.out.println("Exiting the system.");
                break;
            }
        }
    }

    /**
     * Authenticates the user.
     *
     * @return The authenticated user, or null if authentication fails.
     */
    private Person authenticateUser() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Login Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Login as Guest");
            System.out.println("3. Create an Account");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    Person user = authService.authenticate(username, password);
                    if (user != null) {
                        return user;
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                case 2:
                    String guestId = "guest" + guestCounter++; // Generate a unique guest ID
                    return new Guest(guestId); // Create and return a new Guest with the ID
                case 3:
                    createAccount(scanner);
                    break;
                case 4:
                    return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Creates a new customer account.
     *
     * @param scanner The Scanner object for user input.
     */
    private void createAccount(Scanner scanner) {
        System.out.println("\n=== Create an Account ===");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Generate a unique ID for the customer (optional if Customer already includes ID logic)
        String customerId = "Customer" + (authService.getCustomers().size() + 1);

        Customer newCustomer = new Customer(name, username, password, customerId);
        authService.addCustomer(newCustomer);

        System.out.println("Account created successfully. You can now login.");
    }
}