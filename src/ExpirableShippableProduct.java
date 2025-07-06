import java.time.LocalDate;

public class ExpirableShippableProduct extends ExpirableProduct implements Shippable {

    private int weight;

    public ExpirableShippableProduct(String name, double price, int quantity, LocalDate expiryDate, int weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
