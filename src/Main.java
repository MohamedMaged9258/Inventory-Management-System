import Classes.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList customersLinkedList = Customer.loadCustomersFromFile();
        LinkedList adminLinkedList = Admin.loadAdminsFromFile();
        LinkedList productsLinkedList = Product.loadProductsFromFile();
        LinkedList orderLinkedList = Order.loadOrdersFromFile();
        OrderQueue orderQueue = new OrderQueue(OrderQueue.loadOrderQueue(orderLinkedList));


        Inventory inventory = new Inventory(customersLinkedList, adminLinkedList, productsLinkedList, orderLinkedList, orderQueue);

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
            Customer customer = signInForCustomer(inventory);
        } else if (x == 2) {
            Admin admin = signInForAdmin(inventory);
        } else if (x == 3) {
            Object object = sign_up();
            if (object instanceof Admin) {
                Admin admin = (Admin) object;
                x = 1;
            } else if (object instanceof Customer) {
                Customer customer = (Customer) object;
                x = 2;
            }
        }


        boolean running = true;
        if (x == 1) {
            while (running) {
                System.out.println("""
                        \n
                        0.Quit
                        1.Show Info
                        2.Your Borrowed Books
                        3.Your Returned Books
                        4.Borrow Book
                        5.Return Book
                        6.Lost Book
                        7.Show Fines
                        8.Search by ISBN
                        """);
                System.out.print("Choose A Number: ");
                x = scanner.nextInt();
                switch (x) {

                }
            }
        } else if (x == 2) {
            while (running) {
                System.out.println("""
                        \n
                        0.Quit
                        1.Show Info
                        2.Add new Book
                        3.Check Student Borrowed Books
                        4.Check Borrowed Books
                        5.Check Lost Books
                        6.Check Books
                        7.Sort By Title
                        8.Sort By Author Name
                        9.Sort By ISBN
                        """);
                System.out.print("Choose A Number: ");
                x = scanner.nextInt();
                System.out.println();
                switch (x) {

                }
            }
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

    public static Customer signInForCustomer(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please Enter your User Name: ");
            String CuserName = scanner.next();
            System.out.print("Please Enter Your Password: ");
            String password = scanner.next();
            Object object = inventory.getAdminsLinkedList().searchStudentByUserName(CuserName, inventory.getCustomersLinkedList().getHead());
            if (object instanceof String) {
                System.out.println("Please Check your ID and try again.😊");
            } else {
                if (((Customer) object).getCPassword().equals(password)) {
                    System.out.println("Sign In Success.👌");
                    System.out.println("Welcome " + ((Admin) object).getAName());
                    return ((Customer) object);
                } else System.out.println("The Password Is wrong.🤨");
            }
        }

    }

    public static Object sign_up() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to your University Library \n");
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
                return new Customer(CuserName, password, name);
            } else System.out.println("Please try again.");
        }
    }
}