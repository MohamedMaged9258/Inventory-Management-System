package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
                // ( id , name , price , quantity )
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

//    public static LinkedList SortByQuantity(){
//        // with  every insert we should sort to be kept updated
////    ArrayList products = loadProductsFromFile().fromProductLinkedListToArrayList();
////        int n = products.size();
////          for(int i=n-1 ; i >=0  ;i--){
////              Product p = (Product) products.get(i);
////              int q = p.getQuantityInStock();
////              Product p1 = (Product) products.get(i-1);
////              int q1 = p1.getQuantityInStock();
////              if(q>q1){
////                  Product temp = p;
////                  p = p1 ;
////                  p1 = temp;
////              }
////          }
////          LinkedList sorted = products.fromProductArrayListToLinkedList();  // 2zaaay error
////          return sorted ;
//        ArrayList products = loadProductsFromFile().fromProductLinkedListToArrayList();
//        int n = products.size();
//        for(int i =0;i<n ;i++){
//
//
//
//            }
//        }
//
//    }


    public LinkedList sortedByQuantity_descendingly(LinkedList l) {
        ArrayList products = loadProductsFromFile().fromProductLinkedListToArrayList();

        ArrayList<Product> new_products = new ArrayList<>();

        while (!products.isEmpty()){
            int n = products.size();
            Product p = (Product) products.get(0);
            int max =p.getQuantityInStock();
            int index =0;
        for (int i =0;i<n;i++){
            Product p1 = (Product) products.get(i);
            if(p1.getQuantityInStock()>max){
                max = p1.getQuantityInStock();
                index=i;
            }
            new_products.add((Product) products.get(index));
            products.remove(index);
        }
        }
// 7ta5od kol mra loop 2a2el mn el loop eli 2blaha bw7da 34an ban5od 2kbr rkm n4iilo w
// n7to fi array list gdida 34an yb2a trtibhom mn el kbiir lel so8ir f yb2o mtrtbin

       // return new_products.fromArrayListToLinkedList();
    }

    public LinkedList sortedByQuantity_ascendingly(LinkedList l) {
        ArrayList products = loadProductsFromFile().fromProductLinkedListToArrayList();

        ArrayList<Product> new_products = new ArrayList<>();

        while (!products.isEmpty()){
            int n = products.size();
            Product p = (Product) products.get(0);
            int min =p.getQuantityInStock();
            int index =0;
            for (int i =0;i<n;i++){
                Product p1 = (Product) products.get(i);
                if(p1.getQuantityInStock()<min){
                    min = p1.getQuantityInStock();
                    index=i;
                }
                new_products.add((Product) products.get(index));
                products.remove(index);
            }
        }
// 7ta5od kol mra loop 2a2el mn el loop eli 2blaha bw7da 34an ban5od 2kbr rkm n4iilo w
// n7to fi array list gdida 34an yb2a trtibhom mn el so8ir lel kbir f yb2o mtrtbin

        // return new_products.fromArrayListToLinkedList();
    }

      public void SortByName(){
        //stay tuned XD
      }


}
