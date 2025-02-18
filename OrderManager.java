import java.util.ArrayList;  
import java.util.List;  

public class OrderManager {  
    private List<Order> orders = new ArrayList<>();  
    private Inventory inventory;  

    public OrderManager(Inventory inventory) {  
        this.inventory = inventory;  
    }  

    public boolean placeOrder(int productId, int quantity) {  
        Product product = inventory.findProductById(productId);  
        if (product != null && product.getStock() >= quantity) {  
            Order order = new Order(orders.size() + 1, product, quantity);  
            orders.add(order);  
            product.reduceStock(quantity);  
            System.out.println("Order placed successfully: " + order);  
            return true;  
        } else {  
            System.out.println("Order failed: Product not available or insufficient stock.");  
            return false;  
        }  
    }  

    public List<Order> getOrders() {  
        return orders;  
    }  
}
