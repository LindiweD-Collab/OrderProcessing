import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  

public class DatabaseSetup {  
    public static void main(String[] args) {  
        String url = "jdbc:sqlite:shop.db";  
        
        try (Connection conn = DriverManager.getConnection(url);  
             Statement stmt = conn.createStatement();  
             BufferedReader reader = new BufferedReader(new FileReader("scripts/setup.sql"))) {  
            
            StringBuilder sql = new StringBuilder();  
            String line;  
            while ((line = reader.readLine()) != null) {  
                sql.append(line);  
                sql.append("\n");  
            }  
            stmt.execute(sql.toString());  
            System.out.println("Database setup completed.");  
        } catch (SQLException | IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
