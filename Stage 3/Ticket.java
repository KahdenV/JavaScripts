public class Ticket {
    private Showtime showtime;
    private Seat seat;
    private double price;

    public Ticket(Showtime showtime, Seat seat, double price) {
        this.showtime = showtime;
        this.seat = seat;
        this.price = price;
    }
} 