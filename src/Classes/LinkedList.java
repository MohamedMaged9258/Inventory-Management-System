package Classes;

import java.util.ArrayList;
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

    public void insert(Object object) {
        Node current = head;
        if (head == null) {
            head = new Node(null, object);
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(null, object);
        }
        this.size++;
    }

    public static void viewData(LinkedList linkedList) {
        Node current = linkedList.getHead();
        while (current.next != null) {
            System.out.println(((Order) current.data).info());
            current = current.next;
        }
        System.out.println(((Order) current.data).info());
    }

    public void removeCustomer(Customer customer) {
        Node current = head;
        if (current.data == customer) {
            if (current.next != null) {
                head = head.next;
                System.out.println("Removed");
            } else {
                head = null;
                System.out.println("Removed");
            }
        } else {
            while (current.next != null && current.next.data != customer) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = current.next.next;
                System.out.println("Removed");
            } else System.out.println("Customer Not Found");
        }
    }

    public void removeAdmin(Admin admin) {
        Node current = head;
        if (head != null) {
            if (current.data == admin) {
                if (current.next != null) {
                    head = head.next;
                    System.out.println("Removed");
                } else {
                    head = null;
                    System.out.println("Removed");
                }
            } else {
                while (current.next != null && current.next.data != admin) {
                    current = current.next;
                }
                if (current.next != null) {
                    current.next = current.next.next;
                    System.out.println("Removed");
                    return;
                } else System.out.println("Admin Not Found");
            }
        }
        System.out.println("Admin Not Found");
    }

    public Object searchById(int productId, Node root) {
        if (root != null) {
            Product product = (Product) root.data;
            if (product.getProductId() != productId && root.next == null) {
                return "Not Found";
            } else if (product.getProductId() == productId) {
                return root;
            }
            return searchById(productId, root.next);
        } else return "Not Found";
    }

    public Object searchByName(String productName, Node root) {
        if (root != null) {
            Product product = (Product) root.data;
            if (!Objects.equals(product.getProductName(), productName) && root.next == null) {
                return "Not Found";
            } else if (product.getProductName().equals(productName)) {
                return root;
            }
            return searchByName(productName, root.next);
        } else return "Not Found";
    }

    public Object searchCustomerByUserName(String userName, Node root) {
        if (root != null) {
            Customer customer = (Customer) root.data;
            if (!Objects.equals(customer.getUserName(), userName) && root.next == null) {
                return "Not Found";
            } else if (customer.getUserName().equals(userName)) {
                return root;
            }
            return searchCustomerByUserName(userName, root.next);
        }else return "Not Found";
    }

    public Object searchAdminByUserName(String userName, Node root) {
        if (root != null) {
            Admin customer = (Admin) root.data;
            if (!Objects.equals(customer.getUserName(), userName) && root.next == null) {
                return "Not Found";
            } else if (customer.getUserName().equals(userName)) {
                return root;
            }
            return searchAdminByUserName(userName, root.next);
        } else return "Not Found";
    }

    public void displayProduct() {
        Node current = head;
        if (current != null) {
            while (current.next != null) {
                System.out.println(((Product) current.data).info());
                current = current.next;
            }
            System.out.println(((Product) current.data).info());
        }

    }

    public ArrayList<Product> fromProductLinkedListToArrayList() {
        ArrayList<Product> productArrayList = new ArrayList<>();
        Node current = head;
        if (current != null) {
            while (current.next != null) {
                productArrayList.add((Product) current.data);
                current = current.next;
            }
            productArrayList.add((Product) current.data);
        }
        return productArrayList;
    }

    public ArrayList<Customer> fromCustomerLinkedListToArrayList() {
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        Node current = head;
        if (current != null) {
            while (current.next != null) {
                customerArrayList.add((Customer) current.data);
                current = current.next;
            }
            customerArrayList.add((Customer) current.data);
        }
        return customerArrayList;
    }

    public ArrayList<Admin> fromAdminLinkedListToArrayList() {
        ArrayList<Admin> adminArrayList = new ArrayList<>();
        Node current = head;
        if (current != null) {
            while (current.next != null) {
                adminArrayList.add((Admin) current.data);
                current = current.next;
            }
            adminArrayList.add((Admin) current.data);
        }
        return adminArrayList;
    }

    public ArrayList<Order> fromOrderLinkedListToArrayList() {
        ArrayList<Order> orderArrayList = new ArrayList<>();
        Node current = head;
        if (current != null) {
            while (current.next != null) {
                orderArrayList.add((Order) current.data);
                current = current.next;
            }
            orderArrayList.add((Order) current.data);
        }
        return orderArrayList;
    }

    public ArrayList<Report> fromReportLinkedListToArrayList() {
        ArrayList<Report> reportArrayList = new ArrayList<>();
        Node current = head;
        if (current != null) {
            while (current.next != null) {
                reportArrayList.add((Report) current.data);
                current = current.next;
            }
            reportArrayList.add((Report) current.data);
        }
        return reportArrayList;
    }

    public static LinkedList fromProductArrayListToLinkedList(ArrayList<Product> productArrayList) {
        LinkedList productLinkedList = new LinkedList();
        for (Product product : productArrayList) {
            productLinkedList.insert(product);
        }
        return productLinkedList;
    }

    public void clear() {
        head = null;
        size = 0;
    }
}
