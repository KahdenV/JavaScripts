public class Ticket {
    private String ticketId;
    private String customerId;
    private Showtime showtime;
    private Movie movie;

    public Ticket(String ticketId, String customerId, Showtime showtime, Movie movie) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.showtime = showtime;
        this.movie = movie;
    }

    // Getters and toString() for displaying ticket details
    public String getTicketId() {
        return ticketId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", User ID: " + customerId + ", Movie: " 
                + movie.getMovieTitle() + ", Showtime: " + showtime.getTime();
    }
}