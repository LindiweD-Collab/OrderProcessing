import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

public class LoginFrame extends JFrame {  
    private Connection connection;  
    private UserManager userManager;  

    public LoginFrame(Connection connection) {  
        this.connection = connection;  
        userManager = new UserManager(connection);  
        
        setTitle("Login");  
        setSize(300, 150);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLocationRelativeTo(null);  

        JPanel panel = new JPanel();  
        panel.setLayout(new GridBagLayout());  
        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.fill = GridBagConstraints.HORIZONTAL;  

        JLabel usernameLabel = new JLabel("Username:");  
        JTextField usernameField = new JTextField(15);  
        JLabel passwordLabel = new JLabel("Password:");  
        JPasswordField passwordField = new JPasswordField(15);  
        JButton loginButton = new JButton("Login");  
        JButton registerButton = new JButton("Register");  

        gbc.gridx = 0;  
        gbc.gridy = 0;  
        panel.add(usernameLabel, gbc);  
        gbc.gridx = 1;  
        panel.add(usernameField, gbc);  
        gbc.gridx = 0;  
        gbc.gridy = 1;  
        panel.add(passwordLabel, gbc);  
        gbc.gridx = 1;  
        panel.add(passwordField, gbc);  
        gbc.gridx = 0;  
        gbc.gridy = 2;  
        panel.add(loginButton, gbc);  
        gbc.gridx = 1;  
        panel.add(registerButton, gbc);  

        loginButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String username = usernameField.getText();  
                String password = new String(passwordField.getPassword());  
                if (userManager.authenticate(username, password)) {  
                    JOptionPane.showMessageDialog(null, "Login successful!");  
                    dispose();  // Close the login frame  
                    new MainFrame(userManager, inventory).setVisible(true);  // Open Main Frame  
                } else {  
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");  
                }  
            }  
        });  

        registerButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String username = usernameField.getText();  
                String password = new String(passwordField.getPassword());  
                try {  
                    userManager.addUser(username, password);  
                    JOptionPane.showMessageDialog(null, "User registered successfully!");  
                } catch (SQLException ex) {  
                    JOptionPane.showMessageDialog(null, "Error during registration: " + ex.getMessage());  
                }  
            }  
        });  

        add(panel);  
    }  

    public static void main(String[] args) {  
        try {  
            Connection connection = DriverManager.getConnection("jdbc:sqlite:shop.db");  
            LoginFrame loginFrame = new LoginFrame(connection);  
            loginFrame.setVisible(true);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}
