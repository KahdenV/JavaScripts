/**
 * Class representing a guest user.
 */
public class Guest extends Person {
    private static int guestIdCounter = 1; // Static counter for unique guest IDs
    private final String guestId;

    public Guest() {
        super("Guest", "N/A", "N/A"); // Guests don't have names, emails, or passwords
        this.guestId = generateUniqueGuestId();
    }

    /**
     * Generates a unique ID for each guest.
     * @return A unique guest ID as a string.
     */
    private static synchronized String generateUniqueGuestId() {
        return "Guest" + guestIdCounter++;
    }

    public String getGuestId() {
        return guestId;
    }
}