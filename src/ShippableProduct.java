public class ShippableProduct extends Product implements Shippable {

    private int weight;

    public ShippableProduct(String name, double price, int quantity, int weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
