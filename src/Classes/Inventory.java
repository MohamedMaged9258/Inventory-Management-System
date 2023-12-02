package Classes;

//TODO Intialization of the Inventory (Task 1.2: Develop methods in the ++Inventory class++ for (adding new products,
//                  updating stock levels, and retrieving product information.)
//						(By Using Data Structures: Arrays || Linked List) )

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

    public void addProduct(Product product){
        this.productsLinkedList.insert(product);
    }

    public String productInformation(Product product){
        return product.info();
    }


        public void viewProducts() {
            Node current = productsLinkedList.getHead();
            while (current != null) {
                productInformation((Product) current.data);
                current = current.next;
            }
        }

        public Product getProductByName(String productName) {
            Node current = productsLinkedList.getHead();
            while (current != null) {
                Product product = (Product) current.data;
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    return product;
                }
                current = current.next;
            }
            return null;
        }
    public void displayProducts() {
        System.out.println("List of Products:");
        viewProducts();
    }
    }





