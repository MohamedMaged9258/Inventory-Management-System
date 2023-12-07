package Classes;

//TODO Intialization of the Inventory (Task 1.2: Develop methods in the ++Inventory class++ for (adding new products,
//                  updating stock levels, and retrieving product information.)
//						(By Using Data Structures: Arrays || Linked List) )

import java.util.ArrayList;

public class Inventory {
    LinkedList customersLinkedList = new LinkedList();
    LinkedList adminsLinkedList = new LinkedList();
    LinkedList productsLinkedList = new LinkedList();
    LinkedList orderLinkedList = new LinkedList();
    OrderQueue orderQueue = new OrderQueue();

    public Inventory(LinkedList customersLinkedList, LinkedList adminsLinkedList, LinkedList productsLinkedList, LinkedList orderLinkedList, OrderQueue orderQueue) {
        this.customersLinkedList = customersLinkedList;
        this.adminsLinkedList = adminsLinkedList;
        this.productsLinkedList = productsLinkedList;
        this.orderLinkedList = orderLinkedList;
        this.orderQueue = orderQueue;
    }

    public LinkedList getCustomersLinkedList() {
        return customersLinkedList;
    }

    public LinkedList getAdminsLinkedList() {
        return adminsLinkedList;
    }

    public LinkedList getProductsLinkedList() {
        return productsLinkedList;
    }

    public LinkedList getOrderLinkedList() {
        return orderLinkedList;
    }

    public void addProduct(Product product) {
        this.productsLinkedList.insert(product);
    }

    public void productInformation(Product product) {
        System.out.println(product.info());
    }

    public void removeCustomer(Customer customer){
        customersLinkedList.removeCustomer(customer);
    }

    public void viewProducts() {
        productsLinkedList.displayProduct();
    }

    public Product getProductByName(String productName) {
        Node current = productsLinkedList.getHead();
        while (current != null) {
            Product product = (Product) current.data;
            if (product.getProductName().equals(productName)) {
                return product;
            }
            current = current.next;
        }
        return null;
    }

    public void placeOrder(Customer customer) {
        Order order = new Order(customer.getCName(), customer.getShopCart().fromProductLinkedListToArrayList(), customer.getCart_Bill());
        orderLinkedList.insert(order);
        orderQueue.enqueue(order);
    }

    public void displayProducts() {
        System.out.println("List of Products:");
        viewProducts();
    }

    public void save() {
        ArrayList<Customer> customerArrayList = customersLinkedList.fromCustomerLinkedListToArrayList();
        for (Customer customer : customerArrayList) {
            Customer.saveCustomerToFile(customer);
        }
        ArrayList<Admin> adminArrayList = adminsLinkedList.fromAdminLinkedListToArrayList();
        for (Admin admin : adminArrayList) {
            Admin.saveAdminToFile(admin);
        }
        ArrayList<Product> productArrayList = productsLinkedList.fromProductLinkedListToArrayList();
        for (Product product : productArrayList) {
            Product.saveProductToFile(product);
        }
        ArrayList<Order> orderArrayList = orderLinkedList.fromOrderLinkedListToArrayList();
        for (Order order : orderArrayList) {
            Order.saveOrderToFile(order);
        }
    }
}





