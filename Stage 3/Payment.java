public class Payment {
    private double amount;
    private String paymentMethod;
    private String status;

    public Payment(double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "PENDING";
    }
} 