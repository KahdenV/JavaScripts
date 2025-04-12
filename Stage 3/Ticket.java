public class Ticket {
    protected String ticketID;
    protected Customer ticketCustomer;
    protected Showtime ticketShowtime;
    protected String seatNumber;
    protected double ticketPrice;
    protected String ticketStatus;

    // Default constructor
    public Ticket() {
    }

    // Getter for unique ticket identifier
    public String getTicketID() {
        return ticketID;
    }

    // Getter for customer
    public Customer getTicketCustomer() {
        return ticketCustomer;
    }

    // Getter for ticket showtime
    public Showtime getShowtime() {
        return ticketShowtime;
    }

    // Getter for ticket seat number
    public String getSeatNumber() {
        return seatNumber;
    }

    // Getter for ticket price
    public double getTicketPrice() {
        return ticketPrice;
    }

    // Getter for ticket status
    public String getTicketStatus() {
        return ticketStatus;
    }

    // Setter for unique ticket identifier
    public void setTicketID(String newTicketID) {
        ticketID = newTicketID;
    }

    // Setter for customer
    public void setCustomer(Customer newCustomer) {
        ticketCustomer = newCustomer;
    }

    // Setter for ticket showtime
    public void setShowtime(Showtime newShowtime) {
        ticketShowtime = newShowtime;
    }

    // Setter for ticket seat number
    public void setSeatNumber(String newSeatNumber) {
        seatNumber = newSeatNumber;
    }

    // Setter for ticket price
    public void setTicketPrice(double newTicketPrice) {
        ticketPrice = newTicketPrice;
    }

    // Setter for ticket status
    public void setTicketStatus(String newTicketStatus) {
        ticketStatus = newTicketStatus;
    }

    // Print ticket details
    public void printTicketDetails() {
        System.out.println("Customer: " + ticketCustomer.getName());
        System.out.println("Movie: " + ticketShowtime.getShownMovie().getMovieTitle());
        System.out.println("Time: " + ticketShowtime.getTime());
        System.out.println("Screen: " + ticketShowtime.getShowingScreen());
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: " + ticketPrice);
        System.out.println("Status: " + ticketStatus);
    }
}