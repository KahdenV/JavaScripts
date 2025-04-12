import java.util.ArrayList;
import java.util.List;

public class Showtime {
    protected String showtimeID;
    protected Movie shownMovie;
    protected Screen showingScreen;
    protected String time;

    // Static list to store all showtimes
    private static final List<Showtime> showtimeList = new ArrayList<>();

    // Default constructor
    public Showtime() {
        // Empty constructor for default initialization
    }

    public Showtime(Movie shownMovie, Screen showingScreen, String time) {
        this.showtimeID = "Showtime" + System.currentTimeMillis(); // Generate unique ID
        this.shownMovie = shownMovie;
        this.showingScreen = showingScreen;
        this.time = time;

        // Add to the global list
        showtimeList.add(this);
    }

    // Getter for the global showtime list
    public static List<Showtime> getShowtimeList() {
        return showtimeList;
    }

    // Getter for shownMovie
    public Movie getShownMovie() {
        return shownMovie;
    }

    // Getter for showingScreen
    public Screen getShowingScreen() {
        return showingScreen;
    }

    // Getter for time
    public String getTime() {
        return time;
    }

    // Setter for shownMovie
    public void setShownMovie(Movie newShownMovie) {
        this.shownMovie = newShownMovie;
    }

    // Setter for showingScreen
    public void setShowingScreen(Screen newShowingScreen) {
        this.showingScreen = newShowingScreen;
    }

    // Setter for time
    public void setTime(String newTime) {
        this.time = newTime;
    }

    public void printShowtimeDetails() {
        System.out.println("Showtime ID: " + (showtimeID != null ? showtimeID : "No ID assigned"));
        System.out.println("Movie being shown: " +
            (shownMovie != null ? shownMovie.getMovieTitle() : "No movie assigned"));
        System.out.println("Screen number: " +
            (showingScreen != null ? showingScreen.getScreenId() : "No screen assigned"));
        System.out.println("Time: " + (time != null ? time : "No time assigned"));
    }

    @Override
    public String toString() {
        return "Showtime{" +
            "showtimeID='" + showtimeID + '\'' +
            ", shownMovie=" + (shownMovie != null ? shownMovie.getMovieTitle() : "No movie assigned") +
            ", showingScreen=" + (showingScreen != null ? showingScreen.getScreenId() : "No screen assigned") +
            ", time='" + (time != null ? time : "No time assigned") + '\'' +
            '}';
    }
}