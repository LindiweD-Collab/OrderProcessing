import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

public class MainFrame extends JFrame {  
    private Inventory inventory;  
    private UserManager userManager;  
    private Connection connection;  

    public MainFrame(UserManager userManager, Inventory inventory) {  
        this.userManager = userManager;  
        this.inventory = inventory;  

        setTitle("Online Ordering System");  
        setSize(400, 300);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);  

        JTabbedPane tabbedPane = new JTabbedPane();  
        tabbedPane.add("Products", createProductPanel());  
        tabbedPane.add("Orders", createOrderPanel());  

        add(tabbedPane);  
    }  

    private JPanel createProductPanel() {  
        JPanel panel = new JPanel();  
        JTextArea textArea = new JTextArea(10, 30);  
        textArea.setEditable(false);  
        JButton listProductsButton = new JButton("List Products");  

        listProductsButton.addActionListener(e -> {  
            try {  
                textArea.setText("");  
                for (Product product : inventory.getProducts()) {  
                    textArea.append(product.toString() + "\n");  
                }  
            } catch (SQLException ex) {  
                ex.printStackTrace();  
            }  
        });  

        panel.add(listProductsButton);  
        panel.add(new JScrollPane(textArea));  
        return panel;  
    }  

    private JPanel createOrderPanel() {  
        JPanel panel = new JPanel();  
         
        return panel;  
    }  

    public static void main(String[] args) {  
        try {  
            Connection connection = DriverManager.getConnection("jdbc:sqlite:shop.db");  
            UserManager userManager = new UserManager(connection);  
            Inventory inventory = new Inventory(connection);  

              
            inventory.addProduct(new Product(1, "Laptop", 999.99, 10));  
            inventory.addProduct(new Product(2, "Smartphone", 499.99, 20));  
            inventory.addProduct(new Product(3, "Headphones", 199.99, 30));  

            MainFrame frame = new MainFrame(userManager, inventory);  
            frame.setVisible(true);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
