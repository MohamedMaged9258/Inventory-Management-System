package GUI;

import Classes.Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame{
    private JPanel mainPanel;
    private JButton viewProdauctsButton;
    private JButton addItemToCartButton;
    private JButton viewCartButton;
    private JButton placeOrderButton;
    private JButton exitButton;
    private JLabel userName;
    private JLabel price;

    public Customer(Inventory inventory, Classes.Customer customer) {
        userName.setText(customer.getUserName());
        price.setText(String.valueOf(customer.getCart_Bill()) + "$");
        viewProdauctsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point location = getLocation();
                int x = (int) location.getX();
                int y = (int) location.getY();
                Products.main(null, x, y, inventory.getProductsLinkedList(), "Products");
            }
        });
        addItemToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(placeOrderButton, "Order Placed Successfully, Thanks for working with us..",
                        "INFORMATION", JOptionPane.WARNING_MESSAGE);
                inventory.placeOrder(customer);
                customer.placeOrder();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.save();
                Classes.Customer.saveCustomerToFile(customer);
                dispose();
            }
        });
    }

    public static void main(String[] args, int x, int y, Inventory inventory,  Classes.Customer customer) {
        Customer customer1 = new Customer(inventory, customer);
        customer1.setContentPane(customer1.mainPanel);
        customer1.setTitle("Customer");
        customer1.setSize(500, 400);
        customer1.setLocation(x, y);
        customer1.setResizable(false);
        customer1.setVisible(true);
        customer1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
