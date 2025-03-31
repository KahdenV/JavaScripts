import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenNumber;
    private List<Seat> seats;

    public Screen(int screenNumber, int totalSeats) {
        this.screenNumber = screenNumber;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }
} 