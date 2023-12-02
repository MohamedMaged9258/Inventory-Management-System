package Classes;

import java.util.Objects;

public class LinkedList {
    private Node head = null;
    private int size = 0;

    public LinkedList() {
    }

    public Node getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void insert(Object object){
        Node current = head;
        if (this.head == null){
            this.head = new Node(null, object);
            this.size++;
        }else {
            while (current.next != null){
                current = current.next;
            }
            current.next = new Node(null, object);
            this.size++;
        }
    }

    public static void viewData(LinkedList linkedList){
        Node current = linkedList.getHead();
        while (current.next != null){
            System.out.println(((Order)current.data).info());
            current = current.next;
        }
        System.out.println(((Order)current.data).info());
    }

    public Object searchById(int productId, Node root){
        Product product = (Product) root.data;
        if (product.getProductId() != productId && root.next == null){
            return "Not Found";
        }else if (product.getProductId() == productId){
            return root;
        }
        return searchById(productId, root.next);
    }

    public Object searchByName(String productName, Node root){
        Product product = (Product) root.data;
        if (!Objects.equals(product.getProductName(), productName) && root.next == null){
            return "Not Found";
        }else if (product.getProductName().equals(productName)){
            return root;
        }
        return searchByName(productName, root.next);
    }

    public Object searchStudentByUserName(String userName, Node root){
        Customer customer = (Customer) root.data;
        if (!Objects.equals(customer.getCUserName(), userName) && root.next == null){
            return "Not Found";
        }else if (customer.getCUserName().equals(userName)){
            return root;
        }
        return searchByName(userName, root.next);
    }

    public Object searchAdminByUserName(String userName, Node root){
        Admin customer = (Admin) root.data;
        if (!Objects.equals(customer.getAUserName(), userName) && root.next == null){
            return "Not Found";
        }else if (customer.getAUserName().equals(userName)){
            return root;
        }
        return searchByName(userName, root.next);
    }
    
    public void clear() {
        head = null;
        size = 0;
    }
}
