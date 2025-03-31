import java.util.*;

public class MovieTheaterSystem {
    private List<Customer> customers;
    private List<Staff> staff;
    private List<Movie> movies;
    private List<Screen> screens;
    private List<Showtime> showtimes;
    private List<Order> orders;
    private InventoryManager inventoryManager;
    private BookingService bookingService;
    private AuthenticationService authService;

    public MovieTheaterSystem() {
        customers = new ArrayList<>();
        staff = new ArrayList<>();
        movies = new ArrayList<>();
        screens = new ArrayList<>();
        showtimes = new ArrayList<>();
        orders = new ArrayList<>();
        inventoryManager = new InventoryManager();
        bookingService = new BookingService();
        authService = new AuthenticationService();
    }

    public void initialize() {
        // Create dummy data
        createDummyData();
    }

    private void createDummyData() {
        // Add sample customers
        customers.add(new Customer("John Doe", "john@email.com", "pass123"));
        customers.add(new Customer("Jane Smith", "jane@email.com", "pass456"));

        // Add sample staff
        staff.add(new Staff("Admin User", "admin", "admin123", StaffRole.ADMIN));
        staff.add(new Staff("Theater Staff", "staff", "staff123", StaffRole.REGULAR));

        // Add sample movies
        movies.add(new Movie("The Matrix", "Sci-fi", 150));
        movies.add(new Movie("Inception", "Action", 148));

        // Add sample screens
        screens.add(new Screen(1, 50));
        screens.add(new Screen(2, 40));

        // Add sample showtimes
        showtimes.add(new Showtime(movies.get(0), screens.get(0), "2024-04-10 18:00"));
        showtimes.add(new Showtime(movies.get(1), screens.get(1), "2024-04-10 20:00"));
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Movie Theater Management System ===");
            System.out.println("1. Customer Management");
            System.out.println("2. Staff Management");
            System.out.println("3. Movie Management");
            System.out.println("4. Showtime Management");
            System.out.println("5. Booking Management");
            System.out.println("6. Inventory Management");
            System.out.println("7. Reports");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showCustomerMenu();
                    break;
                case 2:
                    showStaffMenu();
                    break;
                case 3:
                    showMovieMenu();
                    break;
                case 4:
                    showShowtimeMenu();
                    break;
                case 5:
                    showBookingMenu();
                    break;
                case 6:
                    showInventoryMenu();
                    break;
                case 7:
                    showReports();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void showCustomerMenu() {
        System.out.println("\n=== Customer Management ===");
        System.out.println("1. View All Customers");
        System.out.println("2. Add New Customer");
        System.out.println("3. Search Customer");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }

    private void showStaffMenu() {
        System.out.println("\n=== Staff Management ===");
        System.out.println("1. View All Staff");
        System.out.println("2. Add New Staff");
        System.out.println("3. Update Staff Role");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }

    private void showMovieMenu() {
        System.out.println("\n=== Movie Management ===");
        System.out.println("1. View All Movies");
        System.out.println("2. Add New Movie");
        System.out.println("3. Update Movie Details");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }

    private void showShowtimeMenu() {
        System.out.println("\n=== Showtime Management ===");
        System.out.println("1. View All Showtimes");
        System.out.println("2. Add New Showtime");
        System.out.println("3. Update Showtime");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }

    private void showBookingMenu() {
        System.out.println("\n=== Booking Management ===");
        System.out.println("1. View All Bookings");
        System.out.println("2. Create New Booking");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }

    private void showInventoryMenu() {
        System.out.println("\n=== Inventory Management ===");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Stock");
        System.out.println("3. Update Stock");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }

    private void showReports() {
        System.out.println("\n=== Reports ===");
        System.out.println("1. Sales Report");
        System.out.println("2. Movie Performance Report");
        System.out.println("3. Customer Activity Report");
        System.out.println("4. Back to Main Menu");
        // Implementation would go here
    }
} 