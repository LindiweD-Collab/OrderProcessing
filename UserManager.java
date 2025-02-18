import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  

public class UserManager {  
    private Connection connection;  

    public UserManager(Connection connection) {  
        this.connection = connection;  
    }  

    public boolean authenticate(String username, String password) {  
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";  
        try (PreparedStatement stmt = connection.prepareStatement(query)) {  
            stmt.setString(1, username);  
            stmt.setString(2, password);  
            ResultSet rs = stmt.executeQuery();  
            return rs.next();    
        } catch (SQLException e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  

    public void addUser(String username, String password) throws SQLException {  
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";  
        try (PreparedStatement stmt = connection.prepareStatement(query)) {  
            stmt.setString(1, username);  
            stmt.setString(2, password);  
            stmt.executeUpdate();  
        }  
    }  
}
