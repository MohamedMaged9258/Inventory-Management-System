import Classes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList customersLinkedList = Customer.loadCustomersFromFile();
        LinkedList adminLinkedList = Admin.loadAdminsFromFile();
        LinkedList productsLinkedList = Product.loadProductsFromFile();
        LinkedList orderLinkedList = Order.loadOrdersFromFile();
        OrderQueue orderQueue = new OrderQueue(OrderQueue.loadOrderQueue(orderLinkedList));


        Inventory inventory = new Inventory(customersLinkedList, adminLinkedList, productsLinkedList, orderLinkedList, orderQueue);
        Customer customer = new Customer();
        Admin admin = new Admin();

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                1.Sign in for User.
                2.Sign in for Admin.
                3.Sign Up.
                """);
        System.out.print("Choose A Number: ");
        int x = scanner.nextInt();
        scanner.nextLine();

        if (x == 1) {
            customer = signInForCustomer(customersLinkedList);
        } else if (x == 2) {
            admin = signInForAdmin(inventory);
        } else if (x == 3) {
            Object object = sign_up();
            if (object instanceof Admin) {
                admin = (Admin) object;
                x = 2;
            } else if (object instanceof Customer) {
                customer = (Customer) object;
                x = 1;
            }
        }

        boolean exit = false;
        if (x == 1) {
            System.out.println();
            inventory.removeCustomer(customer);
            try {
                while (!exit) {
                    System.out.println("Customer Interface:");
                    System.out.println("1. View Products");
                    System.out.println("2. Add Item to Cart");
                    System.out.println("3. View Cart");
                    System.out.println("4. Place Order");
                    System.out.println("5. Exit");
                    System.out.print("Choose A Number: ");
                    int choice = scanner.nextInt();
                    System.out.println();
                    switch (choice) {
                        case 1:
                            inventory.displayProducts();
                            break;
                        case 2:
                            System.out.println("Enter the name of the product to add to the cart:");
                            scanner.nextLine();
                            String productName = scanner.nextLine();
                            Product product = inventory.getProductByName(productName);
                            if (product != null) {
                                customer.addToUserCart(product);
                                System.out.println("Product added to the cart!");
                            } else {
                                System.out.println("Product not found. Please check the name.");
                            }
                            break;
                        case 3:
                            customer.displayCart();
                            break;
                        case 4:
                            inventory.placeOrder(customer);
                            customer.placeOrder();
                            break;
                        case 5:
                            exit = true;
                            inventory.save();
                            Customer.saveCustomerToFile(customer);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (Exception e){
                System.out.println("An unexpected error occurred. Exiting...");
                e.printStackTrace();

                inventory.save();
                Customer.saveCustomerToFile(customer);
            }
        } else if (x == 2) {

        }
    }

    public static Admin signInForAdmin(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your User Name: ");
            String AuserName = scanner.next();
            System.out.print("Please Enter Your Password: ");
            String password = scanner.next();
            Object object = inventory.getAdminsLinkedList().searchAdminByUserName(AuserName, inventory.getAdminsLinkedList().getHead());
            if (object instanceof String) {
                System.out.println("Please Check your ID and try again.😊");
            } else {
                if (((Admin) object).getAPassword().equals(password)) {
                    System.out.println("Sign In Success.👌");
                    System.out.println("Welcome " + ((Admin) object).getAName());
                    return ((Admin) object);
                } else System.out.println("The Password Is wrong.🤨");
            }
        }

    }

    public static Customer signInForCustomer(LinkedList customersLinkedList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your User Name: ");
            String CuserName = scanner.next();
            System.out.print("Please Enter Your Password: ");
            String password = scanner.next();
            Object object = customersLinkedList.searchCustomerByUserName(CuserName, customersLinkedList.getHead());
            if (object instanceof String) {
                System.out.println("Please Check your ID and try again.😊");
            } else if (object instanceof Node){
                Customer customer = (Customer) ((Node) object).getData();
                if (customer.getCPassword().equals(password)) {
                    System.out.println("Sign In Success.👌");
                    System.out.println("Welcome " + customer.getCName());
                    return (customer);
                } else System.out.println("The Password Is wrong.🤨");
            }
        }

    }

    public static Object sign_up() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the inventory management system \n");
        while (true) {
            System.out.println("""
                    1.Admin
                    2.Customers
                    """);
            System.out.print("Choose A Number: ");
            int x = scanner.nextInt();
            System.out.println();
            scanner.nextLine();
            if (x == 1) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your User Name: ");
                String AuserName = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                return new Admin(AuserName, password, name);
            } else if (x == 2) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your User Name: ");
                String CuserName = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                return new Customer(CuserName, password, name, new LinkedList());
            } else System.out.println("Please try again.");
        }
    }
}