package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin {
    private String userName;
    private String password;
    private String name;

    public Admin(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public Admin() {

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void addProduct(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter The Product ID: ");
        int id = scanner.nextInt();
        Object object = inventory.getProductsLinkedList().searchById(id, inventory.getProductsLinkedList().getHead());
        if (object instanceof Node) {
            System.out.println("There is a product with this ID" +
                    "Please Try Again And Be sure you want to edit the product or add new one.");
            return;
        }
        System.out.print("Please Enter The Product Name: ");
        String productName = scanner.next();
        object = inventory.getProductsLinkedList().searchByName(productName, inventory.getProductsLinkedList().getHead());
        if (object instanceof Node) {
            System.out.println("There is a product with this Name" +
                    "Please Try Again And Be sure you want to edit the product or add new one.");
            return;
        }
        System.out.print("Please Enter The Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Please Enter The Product Quantity In Stock: ");
        int stockQuantity = scanner.nextInt();
        Product product = new Product(id, productName, price, stockQuantity);
        inventory.addProduct(product);
        System.out.println(name + " Added Successfully");
    }

    public void productInformationByProductId(int productId, Inventory inventory) {
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchById(productId, products.getHead());
        if (object instanceof String) {
            System.out.println("Please Check the product ID you provided.");
        } else inventory.productInformation((Product) object);
    }

    public void productInformationByProductName(String productName, Inventory inventory) {
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchByName(productName, products.getHead());
        if (object instanceof String) {
            System.out.println("Please Check the product Name you provided.");
        } else inventory.productInformation((Product) object);
    }

    public void productUpdateByProductId(int productId, Inventory inventory) {
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchById(productId, products.getHead());
        if (object instanceof String) {
            System.out.println("Please Check the product ID you provided.");
        } else {
            object = (Node) object;
            Product product = (Product) ((Node) object).data;
            Scanner scanner = new Scanner(System.in);
            boolean exit = true;
            while (exit) {
                System.out.println("""
                        0.Save
                        1.Show Info
                        2.Change Price
                        3.Change Quantity In Stock
                        """);
                System.out.print("Choose A Number: ");
                int x = scanner.nextInt();
                switch (x) {
                    case 0 -> {
                        ((Node) object).data = product;
                        exit = false;
                        break;
                    }
                    case 1 -> productInformationByProductId(productId, inventory);
                    case 2 -> {
                        System.out.print("Please Enter the new price: ");
                        double temp = scanner.nextDouble();
                        product.setPrice(temp);
                    }
                    case 3 -> {
                        System.out.print("Please Enter the new Quantity: ");
                        int temp = scanner.nextInt();
                        product.setQuantityInStock(temp);
                    }
                }
            }
        }
    }

    public void productUpdateByProductName(String productName, Inventory inventory) {
        LinkedList products = inventory.productsLinkedList;
        Object object = products.searchByName(productName, products.getHead());
        if (object instanceof String) {
            System.out.println("Please Check the product ID you provided.");
        } else {
            object = (Node) object;
            Product product = (Product) ((Node) object).data;
            Scanner scanner = new Scanner(System.in);
            boolean exit = true;
            while (exit) {
                System.out.println("""
                        0.Save
                        1.Show Info
                        2.Change Price
                        3.Change Quantity In Stock
                        """);
                System.out.print("Choose A Number: ");
                int x = scanner.nextInt();
                switch (x) {
                    case 0 -> {
                        exit = false;
                        ((Node) object).data = product;
                        break;
                    }
                    case 1 -> productInformationByProductName(productName, inventory);
                    case 2 -> {
                        System.out.print("Please Enter the new Price: ");
                        double temp = scanner.nextDouble();
                        product.setPrice(temp);
                    }
                    case 3 -> {
                        System.out.print("Please Enter the new Quantity: ");
                        int temp = scanner.nextInt();
                        product.setQuantityInStock(temp);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return userName + "/" +
                password + "/" +
                name + "\n";
    }

    public String placeAReport(int productId) {
        Scanner s = new Scanner(System.in);
        String report = "";
        System.out.println("if your report about A lot of product in inventory enter 1 " +
                "\n if you report about A lack of product Quantity in inventory enter 2 ");
        int n = s.nextInt();
        if (n == 1) {
            System.out.print("enter the Quantity of this product");
            int k = s.nextInt();
            report = "the Quantity of " + productId + " " + k;

        }
        if (n == 2) {
            System.out.print("enter the Quantity of this product");
            int f = s.nextInt();
            System.out.println("enter the Quantity we need ");
            int l = s.nextInt();
            report = "the Quantity of" + productId + " " + f + "and we need " + l + "of them ";
        }
        return report;
    }

    public static void saveAdminToFile(Admin admin) {
        try {
            FileWriter writer = new FileWriter("DataBase//Admins.txt", true);
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
