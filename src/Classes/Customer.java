package Classes;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {
    private LinkedList shopCart;
    private String userName;
    private String password;
    private String name;

    public Customer() {
    }
    public Customer(String userName, String password, String name, LinkedList shopCart) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.shopCart = shopCart;
    }
    public String getCUserName() {
        return userName;
    }
    public String getCPassword() {
        return password;
    }
    public String getCName() {
        return name;
    }
    public LinkedList getShopCart() {
        return shopCart;
    }
    public void productInformationByProductName(String productName, Inventory inventory) {
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchByName(productName, products.getHead());
        if (object instanceof String) {
            System.out.println("Please Check the product Name you provided.");
        } else inventory.productInformation((Product) object);
    }
    public void displayCart() {
        if (shopCart.getSize() == 0) {
            System.out.println("Your shop cart is empty." +
                    "Please Add some products to your shopping cart.");
        } else {
            System.out.println("Shopping Cart:");
            shopCart.displayProduct();
            System.out.println("Total Bill: $" + getCart_Bill());
        }
    }
    public void placeOrder() {
        System.out.println("Order placed successfully!");
        shopCart.clear();
    }
    public void addToUserCart(Product product) {
        if (product.quantityInStock == 0) {
            System.out.println("This Product is not available Right Now." +
                    "We will Provide it as Soon as possible.");
        } else {
            product.quantityInStock--;
            shopCart.insert(product);
        }
    }
    public double getCart_Bill() {
        double totalBill = 0.0;
        Node current = shopCart.getHead();
        while (current.next != null) {
            totalBill += ((Product) current.data).getPrice();
            current = current.next;
        }
        totalBill += ((Product) current.data).getPrice();
        return totalBill;
    }
    @Override
    public String toString() {
        return userName + "//" +
                password + "//" +
                name + "//" +
                printArray(shopCart.fromProductLinkedListToArrayList()) + "\n";
    }
    public String printArray(ArrayList<Product> products) {
        String s = "[";
        for (Product product : products) {
            s += product.toStringForOrder();
            s += ",";
        }
        s += "]";
        return s;
    }
    public static void saveCustomerToFile(Customer customer) {
        try {
            FileWriter writer = new FileWriter("DataBase//Customers.txt", true);
            writer.write(customer.toString());
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
                String[] parts = line.split("//");
                ArrayList<Product> shopCart = new ArrayList<>();
                parts[3] = parts[3].replace("[", "").replace("]", "");
                if (!parts[3].isBlank()) {
                    String[] products = parts[3].split(",");
                    for (String product : products) {
                        product = product.replace("(", "").replace(")", "");
                        String[] productParts = product.split("/");
                        Product product2 = new Product(Integer.parseInt(productParts[0]), productParts[1], Double.parseDouble(productParts[2]), Integer.parseInt(productParts[3]));
                        shopCart.add(product2);
                    }
                }
                Customer customer = new Customer(parts[0], parts[1], parts[2], LinkedList.fromProductArrayListToLinkedList(shopCart));
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