import java.util.Scanner;  

public class Main {  
    public static void main(String[] args) {  
        Inventory inventory = new Inventory();  
        OrderManager orderManager = new OrderManager(inventory);  
        
        // Sample products  
        inventory.addProduct(new Product(1, "Laptop", 999.99, 10));  
        inventory.addProduct(new Product(2, "Smartphone", 499.99, 20));  
        inventory.addProduct(new Product(3, "Headphones", 199.99, 30));  

        Scanner scanner = new Scanner(System.in);  
        int choice;  

        do {  
            System.out.println("\n1. List Products");  
            System.out.println("2. Place Order");  
            System.out.println("3. Exit");  
            System.out.print("Enter your choice: ");  
            choice = scanner.nextInt();  

            switch (choice) {  
                case 1:  
                    System.out.println("Available Products:");  
                    for (Product product : inventory.getProducts()) {  
                        System.out.println(product);  
                    }  
                    break;  

                case 2:  
                    System.out.print("Enter Product ID to order: ");  
                    int productId = scanner.nextInt();  
                    System.out.print("Enter quantity: ");  
                    int quantity = scanner.nextInt();  
                    orderManager.placeOrder(productId, quantity);  
                    break;  

                case 3:  
                    System.out.println("Exiting the system.");  
                    break;  

                default:  
                    System.out.println("Invalid choice, please try again.");  
                    break;  
            }  
        } while (choice != 3);  

        scanner.close();  
    }  
}
