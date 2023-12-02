package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin {
    private String AuserName;
    private String Apassword;
    private String Aname;
    public Admin(String AuserName, String Apassword, String Aname) {
        this.AuserName = AuserName;
        this.Apassword = Apassword;
        this.Aname = Aname;
    }
    public Admin() {

    }

    public String getAUserName() {
        return AuserName;
    }

    public String getAPassword() {
        return Apassword;
    }

    public String getAName() {
        return Aname;
    }

    public void addProducts(Product product, Inventory inventory){
        inventory.addProduct(product);
    }

    public void productInformationByProductId(int productId, Inventory inventory){
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchById(productId, products.getHead());
        if (object instanceof String){
            System.out.println("Please Check the product ID you provided.");
        }else inventory.productInformation((Product) object);
    }

    public void productInformationByProductName(String productName, Inventory inventory){
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchByName(productName, products.getHead());
        if (object instanceof String){
            System.out.println("Please Check the product Name you provided.");
        }else inventory.productInformation((Product) object);
    }

    public void productUpdateByProductId(int productId, Inventory inventory){
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchById(productId, products.getHead());
        if (object instanceof String){
            System.out.println("Please Check the product ID you provided.");
        }else {
            object = (Node) object;
            Product product = (Product) ((Node) object).data;
            Scanner scanner = new Scanner(System.in);
            int switchCase = scanner.nextInt();
            while (true){
                System.out.println("""
                        \n
                        0.Save
                        1.Show Info
                        2.Change Price
                        3.Change Quantity In Stock
                        """);
                switch (switchCase){
                    case 0 -> {
                        ((Node) object).data = product;
                        break;
                    }
                    case 1 -> productInformationByProductId(productId, inventory);
                    case 2 -> {
                        double temp = scanner.nextDouble();
                        product.setPrice(temp);
                    }
                    case 3 -> {
                        int temp = scanner.nextInt();
                        product.setQuantityInStock(temp);
                    }
                }
            }
        }
    }

    public void productUpdateByProductName(String productName, Inventory inventory){
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchByName(productName, products.getHead());
        if (object instanceof String){
            System.out.println("Please Check the product ID you provided.");
        }else {
            object = (Node) object;
            Product product = (Product) ((Node) object).data;
            Scanner scanner = new Scanner(System.in);
            int switchCase = scanner.nextInt();
            while (true){
                System.out.println("""
                        \n
                        0.Save
                        1.Show Info
                        2.Change Price
                        3.Change Quantity In Stock
                        """);
                switch (switchCase){
                    case 0 -> {
                        ((Node) object).data = product;
                        break;
                    }
                    case 1 -> productInformationByProductName(productName, inventory);
                    case 2 -> {
                        double temp = scanner.nextDouble();
                        product.setPrice(temp);
                    }
                    case 3 -> {
                        int temp = scanner.nextInt();
                        product.setQuantityInStock(temp);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return AuserName + "/" +
                Apassword + "/" +
                Aname + "\n";
    }

    public static void saveAdminToFile(Admin admin) {
        try {
            FileWriter writer = new FileWriter("Admins.txt", true);
            writer.write(admin.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList loadAdminsFromFile() {
        LinkedList adminArrayList = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("DataBase//Admins.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                Admin admin = new Admin(parts[0], parts[1], parts[2]);
                adminArrayList.insert(admin);
            }
            br.close();
            FileWriter writer = new FileWriter("DataBase//Admins.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return adminArrayList;
    }
}
