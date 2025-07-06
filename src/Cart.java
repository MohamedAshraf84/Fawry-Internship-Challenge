import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (!product.isInStock()) {
            System.out.println(product.getName() + " is out of stock.");
            return;
        }

        if (!product.hasSufficientStock(quantity)) {
            System.out.printf("Only %d units of %s are available.%n", product.getQuantity(), product.getName());
            return;
        }

        CartItem existingItem = findCartItem(product);
        int currentQty = existingItem != null ? existingItem.getQuantity() : 0;
        int totalQty = currentQty + quantity;


        if (existingItem != null) {
            updateItemQuantity(existingItem, totalQty);
        } else {
            cartItems.add(new CartItem(product, quantity));
        }
    }

    private CartItem findCartItem(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }

    private void updateItemQuantity(CartItem item, int newQuantity) {
        item.setQuantity(newQuantity);
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalWeight() {
        return cartItems.stream()
                .filter(item -> item.getProduct() instanceof Shippable)
                .mapToDouble(item -> ((Shippable) item.getProduct()).getWeight() * item.getQuantity())
                .sum() / 1000f;
    }

    public List<Shippable> getShippableItems() {

        return cartItems.stream()
                .map(CartItem::getProduct)
                .filter(product -> product instanceof Shippable)
                .map(product -> (Shippable) product)
                .toList();
    }
}
