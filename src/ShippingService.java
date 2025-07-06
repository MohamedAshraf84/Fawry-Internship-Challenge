import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class ShippingService {
    private List<Shippable> items;

    public ShippingService(List<Shippable> items) {
        this.items = items;
    }

    /*public void shipItems() {
        System.out.println("** Shipping the following items **");
        for (Shippable item : items) {
            System.out.println("- " + item.getName() + " (Weight: " + item.getWeight() + "g)");
        }
    }
*/

   /* public double getShippingFees() {
        *//* needs each item requested quantity
         return items.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();*//*

        return new Random().nextDouble(100) + 1;
    }*/

    public double getShippingFees() {
        return new Random().nextDouble(50) + 1;
    }


}

