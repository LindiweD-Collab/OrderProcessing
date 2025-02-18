import java.util.ArrayList;  
import java.util.List;  

public class Inventory {  
    private List<Product> products = new ArrayList<>();  

    public void addProduct(Product product) {  
        products.add(product);  
    }  

    public List<Product> getProducts() {  
        return products;  
    }  

    public Product findProductById(int id) {  
        for (Product product : products) {  
            if (product.getId() == id) {  
                return product;  
            }  
        }  
        return null;  
    }  
}
