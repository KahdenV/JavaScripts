import java.util.List;
import java.util.Map;

/**
 * Main class for managing the movie theater system.
 */
public class MovieTheaterSystem {
    private List<Customer> customers;
    private List<Staff> staff;
    private AuthenticationService authService;
    private List<Movie> movies;
    private Map<String, Concession> concessions;
    private static int customerIdCounter = 1;

    public MovieTheaterSystem() {
        customers = DummyData.createDummyCustomers();
        staff = DummyData.createDummyStaff();
        movies = DummyData.createDummyMovies();
        concessions = DummyData.createDummyConcessions(); // Initialize dummy concessions

        // Populate the global Concession menu with dummy data
        Concession.getConcessionMenu().putAll(concessions);
    }

    public AuthenticationService getAuthService() {
        return authService;
    }

    /**
     * Initializes the movie theater system.
     */
    public void initialize() {
        // Initialize the auth service with the dummy data
        authService = new AuthenticationService(customers, staff);
    }

    /**
     * Returns the concessions data.
     *
     * @return A map of concession items.
     */
    public Map<String, Concession> getConcessions() {
        return concessions;
    }

    /**
     * Shows the main menu and starts the application.
     */
    public void showMainMenu() {
        MenuManager menuManager = new MenuManager(
            new CustomerMenu(movies, generateUniqueCustomerId(), concessions),
            new StaffMenu(staff, customers, movies, concessions)
        );
        ApplicationManager appManager = new ApplicationManager(menuManager, authService);
        appManager.start();
    }

    /**
     * Generates a unique customer ID dynamically.
     * @return A unique customer ID as a string.
     */
    private synchronized String generateUniqueCustomerId() {
        return "Customer" + customerIdCounter++;
    }

    public static void main(String[] args) {
        // Initialize the MovieTheaterSystem
        MovieTheaterSystem system = new MovieTheaterSystem();
        system.initialize();

        // Start the main menu
        system.showMainMenu();
    }
}