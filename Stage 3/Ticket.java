public class Ticket {
    private String ticketId;
    private String userId; // The ID of the user who purchased the ticket
    private Showtime showtime; // The showtime the ticket is for
    private Movie movie; // The movie being shown

    public Ticket(String ticketId, String userId, Showtime showtime, Movie movie) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.showtime = showtime;
        this.movie = movie;
    }

    // Getters
    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId +
               "\nUser ID: " + userId +
               "\nMovie: " + movie.getMovieTitle() +
               "\nShowtime: " + showtime.getTime() +
               "\nScreen: " + showtime.getShowingScreen().getScreenId();
    }
}