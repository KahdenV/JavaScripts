import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private List<Screen> screens = new ArrayList<>();

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
            System.out.println("11. Manage Screens");
            System.out.println("12. Exit");
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
                    staffMenuOptions(); // Call the method to show staff menu options
                    break;
                case 12:
                    running = false; // Log out and return to the login menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void staffMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Staff Menu ===");
            System.out.println("1. View All Screens");
            System.out.println("2. Add New Screen");
            System.out.println("3. Edit a Screen");
            System.out.println("4. Create and Assign Showtime");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayScreens();
                    break;
                case 2:
                    System.out.println("Enter screen ID:");
                    String screenId = scanner.nextLine();
                    System.out.println("Enter capacity:");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    createScreen(screenId, capacity);
                    break;
                case 3:
                    editScreen(scanner);
                    break;
                case 4:
                    createAndAssignShowtime(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting Staff Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createAndAssignShowtime(Scanner scanner) {
        System.out.println("\n=== Create and Assign Showtime ===");
    
        // Ensure movies exist
        if (movies.isEmpty()) {
            System.out.println("No movies available. Please add movies first.");
            return;
        }
    
        // Display available movies
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).getMovieTitle());
        }
    
        // Select a movie
        System.out.print("Select a movie by number: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (movieChoice < 1 || movieChoice > movies.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Movie selectedMovie = movies.get(movieChoice - 1);
    
        // Create showtime
        System.out.print("Enter showtime (e.g., '12:00 PM'): ");
        String showtimeTime = scanner.nextLine();
    
        // Ensure screens exist
        if (screens.isEmpty()) {
            System.out.println("No screens available. Please add screens first.");
            return;
        }
    
        // Display available screens
        System.out.println("Available Screens:");
        for (int i = 0; i < screens.size(); i++) {
            System.out.println((i + 1) + ". " + screens.get(i).getScreenId());
        }
    
        // Select a screen
        System.out.print("Select a screen by number: ");
        int screenChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (screenChoice < 1 || screenChoice > screens.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Screen selectedScreen = screens.get(screenChoice - 1);
    
        // Create and save the new showtime
        Showtime newShowtime = new Showtime(selectedMovie, selectedScreen, showtimeTime);
        DummyData.addShowtime(newShowtime); // Add to centralized list
    
        System.out.println("Showtime '" + showtimeTime + "' assigned to screen '" + selectedScreen.getScreenId() + "' for movie '" + selectedMovie.getMovieTitle() + "'.");
    }

    // Other methods like createScreen, displayScreens, editScreen, etc., remain unchanged
    public void createScreen(String screenId, int capacity) {
        Screen screen = new Screen(screenId, capacity);
        screens.add(screen);
        System.out.println("Screen created: " + screenId);
    }

    public void displayScreens() {
        if (screens.isEmpty()) {
            System.out.println("No screens available.");
        } else {
            for (Screen screen : screens) {
                System.out.println(screen);
            }
        }
    }

    private void editScreen(Scanner scanner) {
        System.out.println("Enter screen ID to edit:");
        String editScreenId = scanner.nextLine();
        boolean screenFound = false;

        for (Screen screen : screens) {
            if (screen.getScreenId().equals(editScreenId)) {
                screenFound = true;
                System.out.println("1. Update Available Seats");
                System.out.println("2. Add a Showtime");
                System.out.println("Enter your choice:");
                int editChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (editChoice == 1) {
                    System.out.println("Enter new available seats:");
                    int availableSeats = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    screen.updateAvailableSeats(availableSeats);
                } else if (editChoice == 2) {
                    System.out.println("Enter new showtime:");
                    String newShowtime = scanner.nextLine();
                    screen.assignShowTime(newShowtime);
                } else {
                    System.out.println("Invalid option.");
                }
                break;
            }
        }

        if (!screenFound) {
            System.out.println("Screen not found.");
        }
    }

    /**
     * Displays all concession items as inventory.
     */
    private void viewInventory() {
        System.out.println("\n=== Inventory ===");
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
    
        // Create a new movie instance
        Movie newMovie = new Movie();
    
        // Set title
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        newMovie.setMovieTitle(title);
    
        // Set genre using predefined selections
        newMovie.setMovieGenres();
    
        // Set runtime (direct input)
        System.out.print("Enter movie runtime (in minutes): ");
        int runtime = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        newMovie.movieRuntime = runtime; // Directly set runtime instead of using setMovieRuntime()
    
        // Set rating using predefined selections
        newMovie.setMovieRating();
    
        // Set release date
        newMovie.setMovieReleaseDate();
    
        // Add new movie to the list
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