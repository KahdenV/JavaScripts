import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages the application lifecycle, including initialization, shutdown, and user authentication.
 */
public class ApplicationManager {
    private MenuManager menuManager;
    private AuthenticationService authService;
    private int guestCounter = 1; // Counter to track guest IDs
    private int customerCounter = 1; // Counter to track customer IDs
    private List<Movie> movies; // Add movies list
    private Map<String, Concession> concessions; // Add concessions map

    public ApplicationManager(MenuManager menuManager, AuthenticationService authService) {
        this.menuManager = menuManager;
        this.authService = authService;
        this.movies = movies; // Initialize movies
        this.concessions = concessions; // Initialize concessions
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
                if (authenticatedUser instanceof Guest) {
                    System.out.println("Logged in as: " + ((Guest) authenticatedUser).getId());
                } else if (authenticatedUser instanceof Customer) {
                    System.out.println("Logged in as: " + ((Customer) authenticatedUser).getCustomerId());
                }
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
                        // Dynamically set up the CustomerMenu
                        menuManager.setCustomerMenu(new CustomerMenu(movies, user, concessions));
                        return user;
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                case 2:
                    Guest guest = authService.loginAsGuest();
                    // Dynamically set up the CustomerMenu for the guest
                    menuManager.setCustomerMenu(new CustomerMenu(movies, guest, concessions));
                    return guest;
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
        System.out.print("Enter username (email): ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        // Generate a unique ID for the customer
        String customerId = "Customer" + (authService.getCustomers().size() + 1);
    
        // Create a new customer and add it to the list
        Customer newCustomer = new Customer(customerId, name, email, password);
        authService.addCustomer(newCustomer);
    
        System.out.println("Account created successfully. You can now log in.");
    }
}