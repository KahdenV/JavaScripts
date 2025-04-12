public class Showtime {
    
    protected String showtimeID;
    protected Movie shownMovie;
    protected Screen showingScreen;
    protected String time;

    public Showtime(Movie in_shownMovie) {
        this.shownMovie = in_shownMovie;
    }

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

    public void printShowtimeDetails() {
        System.out.println("Movie being shown: " + shownMovie.getMovieTitle());
        System.out.println("Screen number: " + showingScreen.getScreenId());
        System.out.println("Time: " + time);
    }
}