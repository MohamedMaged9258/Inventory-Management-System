package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Order {
    private String orderId;
    private String customerName;
    private ArrayList<Product> productsOrdered;
    private Double totalAmount;



    public Order (String customerName, ArrayList<Product> productsOrdered, Double totalAmount){
        this.orderId = generateId();
        this.productsOrdered = productsOrdered;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }
    public Order (String orderId, String customerName, ArrayList<Product> productsOrdered, Double totalAmount){
        this.orderId = orderId;
        this.productsOrdered = productsOrdered;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    private String generateId() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList getProductsOrdered() {
        return productsOrdered;
    }

    public Double getTotalAmount() { return totalAmount; }

    public String info() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", productsOrdered=" + productsOrdered +
                ", totalAmount=" + totalAmount +
                '}';
    }

    @Override
    public String toString() {
        return orderId + "//" +
                customerName + "//" +
                printArray(productsOrdered) + "//" +
//                productsOrdered.toString() + "//" +
                totalAmount + "\n";
    }

    public String printArray(ArrayList<Product> products){
        String s = "[";
        for (Product product: products){
            s += product.toStringForOrder();
            s += ",";
        }
        s += "]";
        return s;
    }

    public static void saveOrderToFile(Order order) {
        try {
            FileWriter writer = new FileWriter("DataBase//Orders.txt", true);
            writer.write(order.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList loadOrdersFromFile() {
        LinkedList orders = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("DataBase//Orders.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("//");
                ArrayList<Product> productArrayList = new ArrayList<>();
                parts[2] = parts[2].replace("[", "").replace("]", "");
                if (!parts[2].isBlank()) {
                    String[] products = parts[2].split(",");
                    for (String product : products) {
                        product = product.replace("(", "").replace(")", "");
                        String[] productParts = product.split("/");
                        Product product2 = new Product(Integer.parseInt(productParts[0]), productParts[1], Double.parseDouble(productParts[2]), Integer.parseInt(productParts[3]));
                        productArrayList.add(product2);
                    }
                }

                Order order = new Order(parts[0], parts[1], productArrayList, Double.parseDouble(parts[3]));

                orders.insert(order);
            }
            br.close();
            FileWriter writer = new FileWriter("DataBase//Orders.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return orders;
    }
}
