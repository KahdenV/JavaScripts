import java.util.List;

/**
 * Main class for managing the movie theater system.
 */
public class MovieTheaterSystem {
    private List<Customer> customers;
    private List<Staff> staff;
    private AuthenticationService authService;

    public MovieTheaterSystem() {
        customers = DummyData.createDummyCustomers();
        staff = DummyData.createDummyStaff();
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
     * Shows the main menu and starts the application.
     */
    public void showMainMenu() {
        MenuManager menuManager = new MenuManager(new CustomerMenu(), new StaffMenu(staff, customers));
        ApplicationManager appManager = new ApplicationManager(menuManager, authService);
        appManager.start();
    }

    public static void main(String[] args) {
        // Initialize the MovieTheaterSystem
        MovieTheaterSystem system = new MovieTheaterSystem();
        system.initialize();

        // Start the main menu
        system.showMainMenu();
    }
}