public class Order {  
    private int orderId;  
    private Product product;  
    private int quantity;  

    public Order(int orderId, Product product, int quantity) {  
        this.orderId = orderId;  
        this.product = product;  
        this.quantity = quantity;  
    }  

    public int getOrderId() {  
        return orderId;  
    }  

    public Product getProduct() {  
        return product;  
    }  

    public int getQuantity() {  
        return quantity;  
    }  

    public double getTotalPrice() {  
        return product.getPrice() * quantity;  
    }  

    @Override  
    public String toString() {  
        return String.format("Order ID: %d, Product: %s, Quantity: %d, Total Price: %.2f", orderId, product.getName(), quantity, getTotalPrice());  
    }  
}
