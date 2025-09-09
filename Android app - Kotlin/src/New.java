class Rating {
    double rate;
    int count;

    Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }
}

class Product {
    int id;
    String title;
    double price;
    String category;
    Rating rating;

    Product(int id, String title, double price, String category, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.rating = rating;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return product.price * quantity;
    }
}

