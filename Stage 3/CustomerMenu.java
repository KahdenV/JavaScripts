import java.util.List;
import java.util.Scanner;
import java.util.Map;

/**
 * Displays the customer menu and handles customer actions.
 */
public class CustomerMenu {
    private List<Movie> movies;
    private String customerId;
    private Map<String, Concession> concessions;
    private List<Showtime> showtimes;

    public CustomerMenu(List<Movie> movies, String customerId, Map<String, Concession> concessions) {
        this.movies = movies;
        this.customerId = customerId;
        this.concessions = concessions;
    
        // Use centralized showtimes
        this.showtimes = DummyData.getShowtimes();
    }

    public void showCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. View All Movies");
            System.out.println("2. View All Showtimes");
            System.out.println("3. Search Movie");
            System.out.println("4. Book Ticket");
            System.out.println("5. View Concessions");
            System.out.println("6. Purchase Concessions");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllMovies();
                    break;
                case 2:
                    viewAllShowtimes();
                    break;
                case 3:
                    searchMovie(scanner);
                    break;
                case 4:
                    bookTicket(scanner);
                    break;
                case 5:
                    viewConcessions();
                    break;
                case 6:
                    purchaseConcessions(scanner);
                    break;
                case 7:
                    running = false; // Log out and return to the login menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays all showtimes.
     */
    private void viewAllShowtimes() {
        System.out.println("\n=== View All Showtimes ===");
        if (showtimes == null || showtimes.isEmpty()) {
            System.out.println("No showtimes available.");
            return;
        }

        for (Showtime showtime : showtimes) {
            showtime.printShowtimeDetails(); // Print details for each showtime
        }
    }

    /**
     * Displays all available concession items.
     */
    private void viewConcessions() {
        System.out.println("\n=== Concessions Menu ===");
        // Fetch the latest concession data from the global map
        Map<String, Concession> globalConcessions = Concession.getConcessionMenu();
        if (globalConcessions.isEmpty()) {
            System.out.println("No concession items available.");
        } else {
            for (Concession item : globalConcessions.values()) {
                System.out.println(item.getConcessionId() + ": " + item.getItemName() + " - $" + item.getPrice());
            }
        }
    }

    /**
     * Displays all movies.
     */
    private void viewAllMovies() {
        System.out.println("\n=== View All Movies ===");
        for (Movie movie : movies) {
            movie.printMovieDetails();
        }
    }


    /**
     * Searches for a movie by title and displays its details along with associated showtimes.
    *
    * @param scanner The Scanner object for user input.
    */
    private void searchMovie(Scanner scanner) {
        System.out.print("\nEnter movie title to search: ");
        String title = scanner.nextLine();
    
        boolean movieFound = false;
    
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equalsIgnoreCase(title)) {
                movie.printMovieDetails();
                movieFound = true;
    
                System.out.println("\n=== Associated Showtimes ===");
                boolean showtimeFound = false;
    
                // Display showtimes for this movie
                if (showtimes != null && !showtimes.isEmpty()) {
                    for (Showtime showtime : showtimes) {
                        if (showtime.getShownMovie().equals(movie)) {
                            showtime.printShowtimeDetails();
                            showtimeFound = true;
                        }
                    }
                }
    
                if (!showtimeFound) {
                    System.out.println("No showtimes available for this movie.");
                }
                return; // Exit after showing the movie and its showtimes
            }
        }
    
        if (!movieFound) {
            System.out.println("Movie not found.");
        }
    }

    /**
     * Books a ticket for the customer and processes the payment.
     *
     * @param scanner The Scanner object for user input.
     */
    private void bookTicket(Scanner scanner) {
        System.out.println("\n=== Book Ticket ===");
        System.out.print("Enter movie title to book: ");
        String movieTitle = scanner.nextLine();

        for (Movie movie : movies) {
            if (movie.getMovieTitle().equalsIgnoreCase(movieTitle)) {
                System.out.print("Enter ticket price: ");
                double ticketPrice = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                // Process payment
                Payment payment = Payment.processPayment(customerId, ticketPrice);
                System.out.println("Ticket booked successfully! Payment ID: " + payment.getPaymentId());
                return;
            }
        }

        System.out.println("Movie not found.");
    }

    /**
     * Allows the customer to purchase a concession item and processes the payment.
     *
     * @param scanner The Scanner object for user input.
     */
    private void purchaseConcessions(Scanner scanner) {
        System.out.println("\n=== Purchase Concessions ===");
        System.out.print("Enter the item ID to purchase: ");
        String itemId = scanner.nextLine();

        Concession item = Concession.getConcessionMenu().get(itemId);
        if (item != null) {
            // Process payment
            Payment payment = Payment.processPayment(customerId, item.getPrice());
            System.out.println("Concession purchased successfully! Payment ID: " + payment.getPaymentId());
        } else {
            System.out.println("Concession item not found.");
        }
    }
}