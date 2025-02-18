import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  

public class Inventory {  
    private Connection connection;  

    public Inventory(Connection connection) {  
        this.connection = connection;  
    }  

    public void addProduct(Product product) throws SQLException {  
        String query = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";  
        try (PreparedStatement stmt = connection.prepareStatement(query)) {  
            stmt.setString(1, product.getName());  
            stmt.setDouble(2, product.getPrice());  
            stmt.setInt(3, product.getStock());  
            stmt.executeUpdate();  
        }  
    }  

    public List<Product> getProducts() throws SQLException {  
        List<Product> products = new ArrayList<>();  
        String query = "SELECT * FROM products";  
        try (Statement stmt = connection.createStatement();  
             ResultSet rs = stmt.executeQuery(query)) {  
            while (rs.next()) {  
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock")));  
            }  
        }  
        return products;  
    }  

    public Product findProductById(int id) throws SQLException {  
        String query = "SELECT * FROM products WHERE id = ?";  
        try (PreparedStatement stmt = connection.prepareStatement(query)) {  
            stmt.setInt(1, id);  
            ResultSet rs = stmt.executeQuery();  
            if (rs.next()) {  
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));  
            }  
            return null;  
        }  
    }  
}
