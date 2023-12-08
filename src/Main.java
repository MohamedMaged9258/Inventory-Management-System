import Classes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList customersLinkedList = Customer.loadCustomersFromFile();
        LinkedList adminLinkedList = Admin.loadAdminsFromFile();
        LinkedList productsLinkedList = Product.loadProductsFromFile();
        LinkedList reportLinkedList = Report.loadReportsFromFile();
        LinkedList orderLinkedList = Order.loadOrdersFromFile();
        OrderQueue orderQueue = new OrderQueue(OrderQueue.loadOrderQueue(orderLinkedList));

        Inventory inventory = new Inventory(customersLinkedList, adminLinkedList, productsLinkedList, reportLinkedList, orderQueue);
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
            inventory.removeCustomer(customer);
        } else if (x == 2) {
            admin = signInForAdmin(adminLinkedList);
            inventory.removeAdmin(admin);
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
            try {
                while (!exit) {
                    System.out.println("Customer Interface:");
                    System.out.println("""
                            1. View Products
                            2. Add Item to Cart
                            3. View Cart
                            4. Place Order
                            5. Exit
                            """);
                    System.out.print("Choose A Number: ");
                    int choice = scanner.nextInt();
                    System.out.println();
                    switch (choice) {
                        case 1:
                            inventory.viewProducts();
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
                            inventory.save();
                            Customer.saveCustomerToFile(customer);
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Exiting...");
                e.printStackTrace();

                inventory.save();
                Customer.saveCustomerToFile(customer);
            }
        } else if (x == 2) {
            System.out.println();
            try {
                while (!exit) {
                    System.out.println("Admin Interface:");
                    System.out.println("""
                            1. View Products
                            2. Add Product
                            3. Edit Product by Product ID
                            4. Edit Product by Product Name
                            5. View Orders
                            6. Place a Report
                            7. Sort Products Alphabetically
                            8. Sort Products by Stock Quantity
                            9. Exit
                            """);
                    System.out.print("Choose A Number: ");
                    int choice = scanner.nextInt();
                    System.out.println();
                    switch (choice) {
                        case 1:
                            inventory.viewProducts();
                            break;
                        case 2:
                            admin.addProduct(inventory);
                            System.out.println();
                            break;
                        case 3:
                            System.out.print("Please Enter the Product ID: ");
                            int id = scanner.nextInt();
                            admin.productUpdateByProductId(id, inventory);
                            break;
                        case 4:
                            System.out.print("Please Enter the Product Name: ");
                            String name = scanner.next();
                            admin.productUpdateByProductName(name, inventory);
                            break;
                        case 5:
                            inventory.viewOrders();
                            break;
                        case 6:
                            System.out.print("Please Enter the Product ID: ");
                            id = scanner.nextInt();
                            inventory.placeReport(admin.placeAReport(id), admin);
                            break;
                        case 7:
                            inventory.sortProductsAlphabetically();
                            break;
                        case 8:
                            inventory.sortProductsByQuantityInStock();
                            break;
                        case 9:
                            inventory.save();
                            Admin.saveAdminToFile(admin);
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Exiting...");
                e.printStackTrace();

                inventory.save();
                Admin.saveAdminToFile(admin);
            }
        }
    }

    public static Admin signInForAdmin(LinkedList adminLinkedList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your User Name: ");
            String userName = scanner.next();
            System.out.print("Please Enter Your Password: ");
            String password = scanner.next();
            Object object = adminLinkedList.searchAdminByUserName(userName, adminLinkedList.getHead());
            if (object instanceof String) {
                System.out.println("Please Check your UserName and try again.😊");
            } else if (object instanceof Node) {
                Admin admin = (Admin) ((Node) object).getData();
                if (admin.getPassword().equals(password)) {
                    System.out.println("Sign In Success.👌");
                    System.out.println("Welcome " + admin.getName());
                    return (admin);
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
                System.out.println("Please Check your UserName and try again.😊");
            } else if (object instanceof Node) {
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
                String userName = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                return new Admin(userName, password, name);
            } else if (x == 2) {
                System.out.print("Please Enter Your Name: ");
                String name = scanner.nextLine();
                System.out.print("Please Enter Your User Name: ");
                String userName = scanner.nextLine();
                System.out.print("Please Enter Your Password: ");
                String password = scanner.next();
                return new Customer(userName, password, name, new LinkedList());
            } else System.out.println("Please try again.");
        }
    }
}