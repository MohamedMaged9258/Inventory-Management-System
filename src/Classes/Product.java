package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Product {
    int productId,quantityInStock;
    String productName;
    double price;

    public Product(int productId, String productName, double price, int quantityInStock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }
    public Product() {}
    public int getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public String info(){
        return "Product{" +
                "productId=" + productId +
                ", productName=" + productName+
                ", quantityInStock='" + quantityInStock +
                ", price=" + price +
                '}' + "\n";
    }
    @Override
    public String toString() {
        return productId + "/" +
                productName + "/" +
                price+ "/" +
                quantityInStock + "\n";
    }
    public String toStringForOrder() {
        return productId + "/" +
                productName + "/" +
                price+ "/" +
                quantityInStock;
    }
    public static void saveProductToFile(Product product) {
        try {
            FileWriter writer = new FileWriter("DataBase//Products.txt", true);
            writer.write(product.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList loadProductsFromFile() {
        LinkedList products = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("DataBase//Products.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                Product product  = new Product(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
                products.insert(product);
            }
            br.close();
            FileWriter writer = new FileWriter("DataBase//Products.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return products;
    }
}