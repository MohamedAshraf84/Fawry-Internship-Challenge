import java.time.LocalDate;

class ExpirableProduct extends Product implements Expirable {

    private final LocalDate expirationDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
    }

    public LocalDate setExpirationDate() {
        return expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(getExpirationDate());
    }
}
