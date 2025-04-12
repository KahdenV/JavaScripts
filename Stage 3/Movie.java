import java.util.Scanner;
public class Movie
{
    protected String movieID;
    protected String movieTitle;
    protected String movieGenres;
    protected int movieRuntime;
    protected String movieRating;
    protected String movieReleaseDate;
    protected Scanner movieScanner;

    public Movie() {
        movieScanner = new Scanner(System.in);
    }

    public void inputMovieDetails() {
        System.out.println("Enter information for new movie: \n");
        movieScanner = new Scanner(System.in);
    
        // Prompt for and set the movie title
        System.out.print("Enter movie title: ");
        String title = movieScanner.nextLine();
        setMovieTitle(title);
    
        setMovieGenres();
        setMovieRuntime();
        setMovieRating();
        setMovieReleaseDate();
    }




    public String getMovieID()
    {
        return movieID;
    }



    public String getMovieTitle()
    {
        return movieTitle;
    }



    public String getMovieGenres()
    {
        return movieGenres;
    }



    public int getMovieRuntime()
    {
        return movieRuntime;
    }



    public String getMovieRating()
    {
        return movieRating;
    }



    public String getMovieReleaseDate()
    {
        return movieReleaseDate;
    }



    public void setMovieID(String replace_movieID)
    {
        movieID = replace_movieID;
    }



    public void setMovieTitle(String newTitle) {
        this.movieTitle = newTitle;
    }



    public void setMovieGenres()
    {
        String[] genreSelection = {"Action", "Adventure", "Animated", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Historical", "Horror", "Musical", "Mystery", "Political", "Romance", "Sci-Fi", "Superhero", "Thriller", "War", "Western"};
        System.out.println("\nGenre Selection: ");
        printSelection(genreSelection);
        System.out.printf("\nPick genre one: ");
        int genre_one = movieScanner.nextInt()-1;
        System.out.printf("Pick genre two: ");
        int genre_two = movieScanner.nextInt()-1;

        if (genre_one > genreSelection.length || genre_two > genreSelection.length || genre_one == -1 || genre_two == -1)
        {
            System.out.println("\nInvalid choice.");
            setMovieGenres();
        }

        if (genre_one<genre_two) {movieGenres = genreSelection[genre_one] + ", " + genreSelection[genre_two];}
        else if (genre_one>genre_two) {movieGenres = genreSelection[genre_two] + ", " + genreSelection[genre_one];}
        else {movieGenres = genreSelection[genre_one];}
    }



    public void setMovieRuntime()
    {
        System.out.printf("\nEnter movie runtime (in minutes): ");
        movieRuntime = movieScanner.nextInt();
    }



    public void setMovieRating()
    {
        String[] ratingSelection = {"G", "PG", "PG-13", "R"};
        System.out.println("\nRating Selection: ");
        printSelection(ratingSelection);
        System.out.printf("\nPick film rating: ");
        int rating = movieScanner.nextInt();

        if (rating > ratingSelection.length || rating == 0)
        {
            System.out.println("Invalid entry.");
            setMovieRating();
        }

        movieRating = ratingSelection[rating-1];
    }



    public void setMovieReleaseDate()
    {
        System.out.printf("\nYear of release: ");
        int releaseYear = movieScanner.nextInt();
        System.out.printf("Month of release (in number format): ");
        int releaseMonth = movieScanner.nextInt();
        System.out.printf("Day of release: ");
        int releaseDate = movieScanner.nextInt();
        movieReleaseDate = releaseMonth + "/" + releaseDate + "/" + releaseYear;
    }



    public void printSelection(String[] printThisList)
    {
        for (int counter = 0; counter < printThisList.length; counter++)
        {
            System.out.println((counter+1) + ") " + printThisList[counter]);
        }
    }



    public void printMovieDetails()
    {
        System.out.println("\n\n" + movieTitle + ":\n");
        System.out.println("Rating: " + movieRating);
        System.out.println("Released: " + movieReleaseDate);
        System.out.println("Runtime: " + movieRuntime + " minutes");
        System.out.println("Genre: " + movieGenres);
    }
}