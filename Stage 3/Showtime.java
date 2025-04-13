import java.util.ArrayList;
import java.util.List;

public class Showtime {

    protected String showtimeID;
    protected Movie shownMovie;
    protected Screen showingScreen;
    protected String time;
    private static List<Showtime> showtimeList = new ArrayList<>(); // Static list to store all showtimes

    // Constructor
    public Showtime(Movie shownMovie, Screen showingScreen, String time) {
        this.shownMovie = shownMovie;
        this.showingScreen = showingScreen;
        this.time = time;
        showtimeList.add(this); // Add the new showtime to the list
    }

    // Getter for the list of all showtimes
    public static List<Showtime> getShowtimeList() {
        return new ArrayList<>(showtimeList); // Return a copy to preserve encapsulation
    }

    // Getter and Setter methods
    public Movie getShownMovie() {
        return shownMovie;
    }

    public Screen getShowingScreen() {
        return showingScreen;
    }

    public String getTime() {
        return time;
    }

    public void setShownMovie(Movie newShownMovie) {
        shownMovie = newShownMovie;
    }

    public void setShowingScreen(Screen newShowingScreen) {
        showingScreen = newShowingScreen;
    }

    public void setTime(String newTime) {
        time = newTime;
    }

    // Method to print showtime details
    public void printShowtimeDetails() {
        System.out.println("Movie being shown: " + shownMovie.getMovieTitle());
        System.out.println("Screen number: " + showingScreen.getScreenId());
        System.out.println("Time: " + time);
    }
}