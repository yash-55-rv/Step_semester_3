public class Cart {
    // Requirements: Private price tracking, final ID
    private final double[] itemPrices;
    private int itemsAdded;
    private final String cartId;

    // Constructor to set fixed maximum number of items and unique ID
    public Cart(String cartId, int maxItems) {
        if (cartId == null || cartId.isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be empty.");
        }
        if (maxItems <= 0) {
            throw new IllegalArgumentException("Cart capacity must be greater than 0.");
        }
        this.cartId = cartId;
        this.itemPrices = new double[maxItems];
        this.itemsAdded = 0; // Hint 2: Keeps track of where the next price goes
    }

    // Requirement: Add an item's price to the cart
    public void addItem(double price) {
        if (price < 0) {
            System.out.println("Invalid price! Price cannot be negative.");
            return;
        }
        if (this.itemsAdded >= this.itemPrices.length) {
            System.out.println("Cart is full! Cannot add item price: " + price);
            return;
        }
        
        this.itemPrices[this.itemsAdded] = price;
        this.itemsAdded++;
    }

    // Requirement: Read-only total computed dynamically on request (Hint 1)
    public double getTotal() {
        double sum = 0.0;
        for (int i = 0; i < this.itemsAdded; i++) {
            sum += this.itemPrices[i];
        }
        return sum;
    }

    // Requirement: Read-only item count
    public int getItemCount() {
        return this.itemsAdded;
    }

    // Read-only access to Cart ID
    public String getCartId() {
        return this.cartId;
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create a cart with capacity for 20 items
        Cart cart = new Cart("CART-5", 20);
        
        // Add item prices: 250, 99, 151
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        // Verify total and item count
        System.out.println("Cart ID: " + cart.getCartId());       // Output: CART-5
        System.out.println("Total Price: " + cart.getTotal());     // Output: 500.0
        System.out.println("Item Count: " + cart.getItemCount());  // Output: 3

        // Note: There is absolutely no method (like cart.getPrices()) to extract individual item details!
    }
}
