public class Customer {
    private String name;
    private double balance;
    private final Cart cart;
    private final Checkout checkout;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
        checkout = new Checkout(this, cart);
    }

    public void addToCart(Product product, int quantity) {
        cart.add(product, quantity);
    }

    public void checkout() {
        checkout.process();
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public String getName() {
        return name;
    }
}
