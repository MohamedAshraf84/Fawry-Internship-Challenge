import java.util.List;

public class RecipetCalacualtor {

    private final Cart cart;
    private final ShippingService shippingService;

    public RecipetCalacualtor(Cart cart) {
        this.cart = cart;
        this.shippingService = new ShippingService(cart.getShippableItems());
    }

    public double getSubtotal() {
        return cart.getCartItems().stream()
                .filter(item -> {
                    Product product = item.getProduct();
                    if (product instanceof Expirable)
                        return !((ExpirableProduct) product).isExpired();
                    return true;
                })
                .mapToDouble(CartItem::getTotalPrice).sum();
    }

    public double getTotalAmount() {
        return getSubtotal() + (cart.getShippableItems().isEmpty() ? 0f : shippingService.getShippingFees());
    }
}
