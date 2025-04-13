import java.util.ArrayList;
import java.util.List;

public class Screen {
    private String screenId;
    private int capacity;
    private int availableSeats;
    private List<String> showTimes = new ArrayList<>();
    private static List<Screen> screens = new ArrayList<>(); // Static list to store all screens

    // Constructor
    public Screen(String screenId, int capacity) {
        this.screenId = screenId;
        this.capacity = capacity;
        this.availableSeats = capacity;
        screens.add(this); // Add to the static list when a new screen is created
    }

    // Getter for the list of all screens
    public static List<Screen> getScreens() {
        return new ArrayList<>(screens); // Return a copy to preserve encapsulation
    }

    // Add a showtime to the screen
    public void addShowtime(String showtime) {
        showTimes.add(showtime);
    }

    // Getters and Setters
    public String getScreenId() {
        return screenId;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public List<String> getShowTimes() {
        return new ArrayList<>(showTimes); // Return a copy to preserve encapsulation
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void updateAvailableSeats(int seats) {
        if (seats > this.capacity || seats < 0) {
            System.out.println("Invalid number of seats. Must be between 0 and " + this.capacity);
        } else {
            this.availableSeats = seats;
        }
    }

    // Method to assign a showtime
    public void assignShowTime(String showTime) {
        this.showTimes.add(showTime);
    }

    // Method to display screen details
    @Override
    public String toString() {
        return "Screen ID: " + screenId + ", Capacity: " + capacity + ", Available Seats: " + availableSeats +
                ", Showtimes: " + showTimes;
    }
}