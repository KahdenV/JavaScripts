import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<Ticket> tickets;
    private List<Concession> concessions;
    private Payment payment;

    public Order(Customer customer) {
        this.customer = customer;
        this.tickets = new ArrayList<>();
        this.concessions = new ArrayList<>();
    }
} 