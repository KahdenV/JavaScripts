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

    public CustomerMenu(List<Movie> movies, Person user, Map<String, Concession> concessions) {
        this.movies = movies;
        this.customerId = user.getId(); // Dynamically fetch the user ID
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
            System.out.println("5. View Your Tickets");
            System.out.println("6. View Concessions");
            System.out.println("7. Purchase Concessions");
            System.out.println("8. Logout");
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
                    buyTicket(scanner);
                    break;
                case 5:
                    viewTickets();
                    break;
                case 6:
                    viewConcessions();
                    break;
                case 7:
                    purchaseConcessions(scanner);
                    break;
                case 8:
                    running = false; // Log out and return to the login menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAllShowtimes() {
        System.out.println("\n=== View All Showtimes ===");
        List<Showtime> showtimes = DummyData.getShowtimes();
    
        if (showtimes.isEmpty()) {
            System.out.println("No showtimes available.");
        } else {
            for (int i = 0; i < showtimes.size(); i++) {
                System.out.println((i + 1) + ". " + showtimes.get(i));
            }
        }
    }

    /**
     * Displays all available concession items.
     */
    public void viewConcessions() {
        System.out.println("\n=== Available Concessions ===");
        if (concessions.isEmpty()) {
            System.out.println("No concessions available.");
        } else {
            for (Map.Entry<String, Concession> entry : concessions.entrySet()) {
                Concession item = entry.getValue();
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

    public void buyTicket(Scanner scanner) {
        System.out.println("\n=== Buy Ticket ===");
        List<Showtime> showtimes = DummyData.getShowtimes();
    
        if (showtimes.isEmpty()) {
            System.out.println("No showtimes available.");
            return;
        }
    
        System.out.println("Available Showtimes:");
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.println((i + 1) + ". " + showtimes.get(i));
        }
    
        // Select a showtime
        System.out.print("Select a showtime by number: ");
        int showtimeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (showtimeChoice < 1 || showtimeChoice > showtimes.size()) {
            System.out.println("Invalid choice.");
            return;
        }
    
        Showtime selectedShowtime = showtimes.get(showtimeChoice - 1);
    
        // Check available seats
        if (selectedShowtime.getAvailableSeats() <= 0) {
            System.out.println("No seats available for this showtime.");
            return;
        }
    
        // Generate a ticket ID and use the correct customer ID
        String ticketId = "TICKET-" + (DummyData.getTicketsByCustomer(customerId).size() + 1);
        Ticket newTicket = new Ticket(ticketId, this.customerId, selectedShowtime, selectedShowtime.getShownMovie());
    
        // Reduce available seats and save the ticket
        selectedShowtime.reduceAvailableSeats(1);
        DummyData.addTicket(this.customerId, newTicket);
    
        System.out.println("Ticket purchased successfully!");
        System.out.println(newTicket);
    }

    public void viewTickets() {
        System.out.println("\n=== Your Tickets ===");
        List<Ticket> tickets = DummyData.getTicketsByCustomer(customerId);
    
        if (tickets.isEmpty()) {
            System.out.println("You have no tickets.");
        } else {
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }
}