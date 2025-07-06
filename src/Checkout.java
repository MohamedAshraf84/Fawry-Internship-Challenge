import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Checkout {
    private final Customer customer;
    private final Cart cart;
    private final RecipetCalacualtor recipetCalacualtor;
    private final ShippingService shippingService;

    public Checkout(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
        this.recipetCalacualtor = new RecipetCalacualtor(cart);
        this.shippingService = new ShippingService(cart.getShippableItems());
    }

    public void process() {
        if (cart.isEmpty()) {
            System.out.println("Can't checkout, cart is empty.");
            return;
        }

        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();

            if (product instanceof Expirable) {
                if (((ExpirableProduct) product).isExpired()) {
                    System.out.println("Cannot checkout: " + product.getName() + " is expired.");
                    return;
                }
            }
        }

        if (hasEnoughBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        customer.deductBalance(recipetCalacualtor.getTotalAmount());
        updateProductQuantities();

        //shippingService.shipItems();

        printShipmentNotice();
        printReceipt();
        System.out.printf("\nCurrent Balance: %.2f $%n", customer.getBalance());
    }


    public boolean hasEnoughBalance() {
        return recipetCalacualtor.getTotalAmount() >= customer.getBalance();
    }

    private List<Product> getShippableItems() {
        List<Product> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();
            if (product instanceof Shippable) shippableItems.add(product);
        }
        return shippableItems;
    }

    private void printShipmentNotice() {
        System.out.println("** Shipment notice **");

        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();
            if (product instanceof Shippable) {
                int quantity = item.getQuantity();
                String name = product.getName();
                int weight = ((Shippable) product).getWeight();

                System.out.printf("%-1s %-10s %-10s%n", quantity + "x", name, quantity * weight + "g");
            }
        }

        System.out.printf("Total package weight %.2f kg\n\n", cart.getTotalWeight());
    }

    private void printReceipt() {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getCartItems()) {

            Product product = item.getProduct();

            if (product instanceof Expirable) {
                if (((ExpirableProduct) product).isExpired()) continue;
            }

            int quantity = item.getQuantity();
            String name = product.getName();
            double price = product.getPrice();
            System.out.printf("%-1s %-10s %5.2f%n", quantity + "x", name, quantity * price);
        }

        String RECEIPT_PRINTING_FORMAT = "%-10s %-5.2f $%n";
        System.out.println("--------------------------");
        System.out.printf(RECEIPT_PRINTING_FORMAT, "Subtotal", recipetCalacualtor.getSubtotal());
        System.out.printf(RECEIPT_PRINTING_FORMAT, "Shipping", cart.getShippableItems().isEmpty() ? 0 : shippingService.getShippingFees() );
        System.out.printf(RECEIPT_PRINTING_FORMAT, "Amount", recipetCalacualtor.getTotalAmount());
    }

    private void updateProductQuantities() {
        for (CartItem item : cart.getCartItems()) {
            Product product = item.getProduct();
            int remainingQuantity = product.getQuantity() - item.getQuantity();
            product.setQuantity(remainingQuantity);
        }
    }
}
