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
    private List<Showtime> showtimes;

    public MovieTheaterSystem() {
        customers = DummyData.createDummyCustomers();
        staff = DummyData.createDummyStaff();
        movies = DummyData.createDummyMovies();
        concessions = DummyData.createDummyConcessions(); // Initialize dummy concessions
        showtimes = DummyData.getShowtimes(); // Initialize showtimes

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

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    /**
     * Shows the main menu and starts the application.
     */
    public void showMainMenu() {
        // Initialize the MenuManager without pre-creating CustomerMenu
        MenuManager menuManager = new MenuManager(
            null, // CustomerMenu will be initialized dynamically
            new StaffMenu(staff, customers, movies, concessions) // StaffMenu remains the same
        );
        ApplicationManager appManager = new ApplicationManager(menuManager, authService);
        appManager.setMovies(movies);
        appManager.setConcessions(concessions);

        // Start the application lifecycle
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