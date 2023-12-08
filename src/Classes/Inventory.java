package Classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Inventory {
    LinkedList customersLinkedList = new LinkedList();
    LinkedList adminsLinkedList = new LinkedList();
    LinkedList productsLinkedList = new LinkedList();
    LinkedList reportLinkedList = new LinkedList();
    OrderQueue orderQueue = new OrderQueue();

    public Inventory(LinkedList customersLinkedList, LinkedList adminsLinkedList, LinkedList productsLinkedList, LinkedList reportLinkedList, OrderQueue orderQueue) {
        this.customersLinkedList = customersLinkedList;
        this.adminsLinkedList = adminsLinkedList;
        this.productsLinkedList = productsLinkedList;
        this.reportLinkedList = reportLinkedList;
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

    public void sortProductsByQuantityInStock() {
        ArrayList<Product> products = productsLinkedList.fromProductLinkedListToArrayList();
        products.sort(
                new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return Integer.compare(p1.getQuantityInStock(), p2.getQuantityInStock());
                    }
                }
        );
        productsLinkedList = LinkedList.fromProductArrayListToLinkedList(products);
        System.out.println("Products had been sorted successfully.");
    }

    public void sortProductsAlphabetically() {
        ArrayList<Product> products = productsLinkedList.fromProductLinkedListToArrayList();
        products.sort(
                new Comparator<Product>() {
                    @Override
                    public int compare(Product p1, Product p2) {
                        return p1.getProductName().compareTo(p2.getProductName());
                    }
                }
        );
        productsLinkedList = LinkedList.fromProductArrayListToLinkedList(products);
        System.out.println("Products had been sorted successfully.");
    }

    public void addProduct(Product product) {
        this.productsLinkedList.insert(product);
    }

    public void productInformation(Product product) {
        System.out.println(product.info());
    }

    public void removeCustomer(Customer customer) {
        customersLinkedList.removeCustomer(customer);
    }

    public void removeAdmin(Admin admin) {
        adminsLinkedList.removeAdmin(admin);
    }

    public void viewProducts() {
        System.out.println("List of Products:");
        productsLinkedList.displayProduct();
    }

    public void viewOrders() {
        Queue queue = orderQueue.orderQueue;
        LinkedList orderLinkedList = new LinkedList();
        Object object = queue.dequeue();
        while (object instanceof Node node) {
            System.out.println(((Order) node.data).info());
            object = queue.dequeue();
        }
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

    public void placeReport(String theReport, Admin publisher) {
        Report report = new Report(theReport, publisher);
        reportLinkedList.insert(report);
    }

    public void placeOrder(Customer customer) {
        Order order = new Order(customer.getCName(), customer.getShopCart().fromProductLinkedListToArrayList(), customer.getCart_Bill());
        orderQueue.enqueue(order);
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
        ArrayList<Report> reportArrayList = reportLinkedList.fromReportLinkedListToArrayList();
        for (Report report : reportArrayList) {
            Report.saveReportToFile(report);
        }
        ArrayList<Order> orderArrayList = orderQueue.fromQueueToLinkedList().fromOrderLinkedListToArrayList();
        for (Order order : orderArrayList) {
            Order.saveOrderToFile(order);
        }
    }
}