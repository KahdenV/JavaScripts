import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String screenId;
    private int capacity;
    private int availableSeats;
    private List<String> showTimes;
    private List<Screen> screens = new ArrayList<>();

    // Constructor
    public Screen(String screenId, int capacity) {
        this.screenId = screenId;
        this.capacity = capacity;
        this.availableSeats = capacity; // Initially, all seats are available
        this.showTimes = new ArrayList<>();
    }

    // Getter for screenId
    public String getScreenId() {
        return screenId;
    }

    // Getter for capacity
    public int getCapacity() {
        return capacity;
    }

    // Getter for available seats
    public int getAvailableSeats() {
        return availableSeats;
    }

    // Assigns a showtime to the screen
    public void assignShowTime(String showTime) {
        showTimes.add(showTime);
    }

    // Updates the number of available seats
    public void updateAvailableSeats(int seatCount) {
        if (seatCount <= capacity && seatCount >= 0) {
            this.availableSeats = seatCount;
        } else {
            System.out.println("Invalid seat count. Must be between 0 and " + capacity);
        }
    }

    // Returns the list of assigned showtimes
    public List<String> getShowTimes() {
        return new ArrayList<>(showTimes); // Return a copy to preserve encapsulation
    }

    // Debug method to display screen information
    @Override
    public String toString() {
        return "Screen ID: " + screenId +
                ", Capacity: " + capacity +
                ", Available Seats: " + availableSeats +
                ", ShowTimes: " + showTimes;
    }

}