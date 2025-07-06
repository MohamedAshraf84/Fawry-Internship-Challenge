import java.time.LocalDate;

public class AppDriveTest {


    public static void main(String[] args)
    {

        /*Test Case 1*/

        Customer customer = new Customer("Mohamed", 15000);
        Product cheese = new ExpirableShippableProduct("Cheese", 30, 5, LocalDate.of(2025, 9, 1), 500);
        Product tv = new ShippableProduct("TV", 800, 2, 20);
        Product ebook = new DigitalProduct("E-Book", 10, 100);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 20, 10, LocalDate.of(2025, 7, 10), 63);
        Product laptop = new ShippableProduct("Laptop", 1000, 3, 5);
        customer.addToCart(cheese, 2);
        customer.addToCart(tv, 1);
        customer.addToCart(ebook, 2);
        customer.addToCart(biscuits, 2);
        customer.addToCart(laptop, 4);

        customer.checkout();


        /*Test Cast 2*/
        /*Customer customer = new Customer("Ashraf", 15000);
        Product tablet = new ShippableProduct("Tablet", 500, 5, 5);
        customer.addToCart(tablet, 1);
        customer.addToCart(tablet, 2);
        customer.checkout();*/


        /*Test Cast 3*/
        /*Customer customer = new Customer("Hasan", 500);
        Product ebook = new DigitalProduct("E-Book", 50, 10);
        Product onlineCourse = new DigitalProduct("Online Course", 200, 2);

        customer.addToCart(ebook, 2);
        customer.addToCart(onlineCourse, 1);

        customer.checkout();
        */


        /*Test Cast 4*/
        /*Customer customer = new Customer("Abdel-Rahman", 1000);

        Product milk = new ExpirableShippableProduct("Milk", 15, 4, LocalDate.of(2025, 7, 6), 100);
        Product cereal = new ExpirableShippableProduct("Cereal", 30, 5, LocalDate.of(2025, 10, 1), 200);

        customer.addToCart(milk, 2);
        customer.addToCart(cereal, 1);

        customer.checkout();*/


        /*Test Case 5*/
        /*Customer customer = new Customer("Abousalama", 100);

        Product phone = new ShippableProduct("Smartphone", 700, 1, 10);
        Product simCard = new DigitalProduct("SIM Card", 50, 5);

        customer.addToCart(phone, 1);
        customer.addToCart(simCard, 1);

        customer.checkout();*/
    }
}
