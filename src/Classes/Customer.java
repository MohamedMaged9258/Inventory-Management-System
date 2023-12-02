package Classes;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {
    private LinkedList shopCart;
    private String CuserName;

    private String Cpassword;

    private String Cname;

    public Customer(){}

    public Customer (String CuserName, String Cpassword, String Cname) {
        this.CuserName = CuserName;
        this.Cpassword = Cpassword;
        this.Cname = Cname;
    }



    //    ================================================================

    public String getCUserName() {
        return CuserName;
    }

    public String getCPassword() {
        return Cpassword;
    }

    public String getCName() {
        return Cname;
    }

//    ================================================================

    public void productInformationByProductName(String productName, Inventory inventory){
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchByName(productName, products.getHead());
        if (object instanceof String){
            System.out.println("Please Check the product Name you provided.");
        }else inventory.productInformation((Product) object);
    }
//=================================================================================
        public void customerface(Inventory inventory) {
            Scanner s = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Customer Interface:");
                System.out.println("1. View Products");
                System.out.println("2. Add Item to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Place Order");
                System.out.println("5. View Order Status");
                System.out.println("6. Exit");

                int choice = s.nextInt();

                switch (choice) {
                    case 1:
                        inventory.displayProducts();
                        break;

                    case 2:

                        System.out.println("Enter the name of the product to add to the cart:");
                        s.nextLine();
                        String productName = s.nextLine();
                        Product product = inventory.getProductByName(productName);
                        if (product != null) {
                            addUserCart(product);
                            System.out.println("Product added to the cart!");
                        } else {
                            System.out.println("Product not found. Please check the name.");
                        }
                        break;

                    case 3:

                        displayCart();
                        break;

                    case 4:

                        placeOrder();
                        break;

                    case 5:

                        viewOrderStatus();
                        break;

                    case 6:

                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void displayCart() {
            System.out.println("Shopping Cart:");
           // shopCart.display();
            System.out.println("Total Bill: $" + getCart_Bill());
        }

        private void placeOrder() {
            System.out.println("Order placed successfully!");
            shopCart.clear();
        }

        private void viewOrderStatus() {
            System.out.println("No orders placed yet.");
        }

//=================================================================================================================

    //TODO LIST addUserCart,getCart_Bill
    public void addUserCart(Product product){
        shopCart.insert(product);
    }

    public double getCart_Bill(){
        double totalBill = 0.0;
        Node current = shopCart.getHead();
        while (current.next != null){
            totalBill += ((Product) current.data).getPrice();
        }
        if (shopCart.getHead() != null){
            totalBill += ((Product) current.data).getPrice();
        }
        return totalBill;
    }

    @Override
    public String toString() {
        return CuserName + "/" +
                Cpassword + "/" +
                Cname + "\n";
    }

    public static void saveAdminToFile(Admin admin) {
        try {
            FileWriter writer = new FileWriter("Customers.txt", true);
            writer.write(admin.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList loadCustomersFromFile() {
        LinkedList customersArrayList = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("DataBase//Customers.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                Customer customer = new Customer(parts[0], parts[1], parts[2]);
                customersArrayList.insert(customer);
            }
            br.close();
            FileWriter writer = new FileWriter("DataBase//Customers.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return customersArrayList;
    }
}
