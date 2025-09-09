import java.util.*;

public class ProductApp {
    static List<Product> products = new ArrayList<>();
    static List<CartItem> cart = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadDummyProducts();

        while (true) {
            System.out.println("\n===== PRODUCT LISTING PAGE =====");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> showProducts();
                case 2 -> showCart();
                case 3 -> {
                    System.out.println("Thank you! Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void showProducts() {
        System.out.println("\n--- Products ---");
        for (Product p : products) {
            System.out.println(p.id + ". " + p.title + " | ₹" + p.price +
                    " | Category: " + p.category + " | Rating: " + p.rating.rate);
        }
        System.out.print("\nEnter product ID to add to cart (0 to go back): ");
        int pid = sc.nextInt();
        if (pid == 0) return;

        Product selected = products.stream().filter(p -> p.id == pid).findFirst().orElse(null);
        if (selected != null) {
            addToCart(selected);
        } else {
            System.out.println("Invalid product ID!");
        }
    }

    static void addToCart(Product p) {
        for (CartItem item : cart) {
            if (item.product.id == p.id) {
                item.quantity++;
                System.out.println(p.title + " quantity updated in cart!");
                return;
            }
        }
        cart.add(new CartItem(p, 1));
        System.out.println(p.title + " added to cart!");
    }

    static void showCart() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty!");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        double total = 0;
        for (CartItem item : cart) {
            System.out.println(item.product.title + " x" + item.quantity + " = ₹" + item.getTotalPrice());
            total += item.getTotalPrice();
        }
        System.out.println("Total Price: ₹" + total);

        System.out.print("Enter product ID to remove (0 to go back): ");
        int pid = sc.nextInt();
        if (pid == 0) return;
        cart.removeIf(item -> item.product.id == pid);
        System.out.println("Item removed from cart!");
    }

    static void loadDummyProducts() {
        products.add(new Product(1, "iPhone 13", 60000, "Electronics", new Rating(4.5, 200)));
        products.add(new Product(2, "Nike Shoes", 4000, "Fashion", new Rating(4.2, 150)));
        products.add(new Product(3, "Samsung TV", 45000, "Electronics", new Rating(4.3, 120)));
        products.add(new Product(4, "Wooden Chair", 2500, "Furniture", new Rating(3.9, 80)));
        products.add(new Product(5, "Laptop", 55000, "Electronics", new Rating(4.6, 300)));
    }
}
