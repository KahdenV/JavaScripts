import java.util.Date; // For the Date class
import java.util.Map; // For the Map interface
import java.util.HashMap; // For the HashMap implementation

public class Payment {
    private final String paymentId;
    private final String customerId;
    private final double amount;
    private final Date paymentDate;
    private String status;

    // Store payment records
    private final static Map<String, Payment> paymentRecords = new HashMap<>();

    // Constructor
    public Payment(String paymentId, String customerId, double amount, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    // Getters
    public String getPaymentId() { return paymentId; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public Date getPaymentDate() { return paymentDate; }
    public String getStatus() { return status; }

    // Process a payment
    public static Payment processPayment(String customerId, double amount) {
        String paymentId = generatePaymentId(); // Generate a unique ID
        Date paymentDate = new Date(); // Current date
        String status = "Completed";

        Payment payment = new Payment(paymentId, customerId, amount, paymentDate, status);
        paymentRecords.put(paymentId, payment); // Save the payment
        System.out.println("Payment processed successfully: " + paymentId);
        return payment;
    }

    // Refund a payment
    public static void refundPayment(String paymentId) {
        Payment payment = paymentRecords.get(paymentId);
        if (payment != null && payment.status.equals("Completed")) {
            payment.status = "Refunded";
            System.out.println("Payment refunded: " + paymentId);
        } else {
            System.out.println("Refund failed. Payment not found or already refunded.");
        }
    }

    // Get payment details
    public static Payment getPaymentDetails(String paymentId) {
        Payment payment = paymentRecords.get(paymentId);
        if (payment != null) {
            return payment;
        } else {
            System.out.println("Payment not found: " + paymentId);
            return null;
        }
    }

    // Get All Payment Records
    public static Map<String, Payment> getPaymentRecords() {
        return paymentRecords;
    }

    // Update payment status
    public static void updatePaymentStatus(String paymentId, String status) {
        Payment payment = paymentRecords.get(paymentId);
        if (payment != null) {
            payment.status = status;
            System.out.println("Payment status updated to: " + status);
        } else {
            System.out.println("Payment not found: " + paymentId);
        }
    }

    // Generate a unique payment ID
    private static String generatePaymentId() {
        return "PAY" + System.currentTimeMillis(); // Example ID generation using timestamp
    }

    // Debug method to display payment information
    @Override
    public String toString() {
        return "Payment ID: " + paymentId +
                ", Customer ID: " + customerId +
                ", Amount: " + amount +
                ", Payment Date: " + paymentDate +
                ", Status: " + status;
    }
}