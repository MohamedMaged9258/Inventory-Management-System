package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {
    private LinkedList shopCart;
    private String CuserName;

    private String Cpassword;

    private String Cname;

    public Customer (String CuserName, String Cpassword, String Cname) {
        this.CuserName = CuserName;
        this.Cpassword = Cpassword;
        this.Cname = Cname;
    }

    public Customer() {

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
