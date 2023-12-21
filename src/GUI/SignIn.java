package GUI;

import Classes.Customer;
import Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SignIn extends JFrame{
    LinkedList customersLinkedList = Classes.Customer.loadCustomersFromFile();
    LinkedList adminLinkedList = Admin.loadAdminsFromFile();
    LinkedList productsLinkedList = Product.loadProductsFromFile();
    LinkedList reportLinkedList = Report.loadReportsFromFile();
    LinkedList orderLinkedList = Order.loadOrdersFromFile();
    OrderQueue orderQueue = new OrderQueue(OrderQueue.loadOrderQueue(orderLinkedList));

    Inventory inventory = new Inventory(customersLinkedList, adminLinkedList, productsLinkedList, reportLinkedList, orderQueue);
    ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel mainPanel;
    private JTextField userNameText;
    private JPasswordField passwordText;
    private JCheckBox showPass;
    private JRadioButton customerButton;
    private JRadioButton adminButton;
    private JButton signInButton;
    private JButton signUpButton;

    public SignIn() {
        buttonGroup.add(customerButton);
        buttonGroup.add(adminButton);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameText.getText();
                String password = passwordText.getText();
                if (customerButton.isSelected()){
                    Object object = inventory.getCustomersLinkedList().searchCustomerByUserName(userName, inventory.getCustomersLinkedList().getHead());
                    if (object instanceof String) {
                        userNameText.setText("");
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(signInButton, "Please Check your UserName and try again.😊",
                                "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else if (object instanceof Node) {
                        Customer customer = (Customer) ((Node) object).getData();
                        if (customer.getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(signInButton, "Sign In Success.👌\n " + "Welcome " + customer.getName(),
                                    "INFORMATION", JOptionPane.WARNING_MESSAGE);
                            inventory.removeCustomer(customer);
                            Point location = getLocation();
                            int x = (int) location.getX();
                            int y = (int) location.getY();
                            GUI.Customer.main(null, x, y, inventory, customer);
                            dispose();
                        } else {
                            passwordText.setText("");
                            JOptionPane.showMessageDialog(signInButton, "The Password Is wrong.🤨",
                                    "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }else if (adminButton.isSelected()){
                    Object object = inventory.getAdminsLinkedList().searchAdminByUserName(userName, inventory.getAdminsLinkedList().getHead());
                    if (object instanceof String) {
                        userNameText.setText("");
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(signInButton, "Please Check your UserName and try again.😊",
                                "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else if (object instanceof Node) {
                        Admin admin = (Admin) ((Node) object).getData();
                        if (admin.getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(signInButton, "Sign In Success.👌\n " + "Welcome " + admin.getName(),
                                    "INFORMATION", JOptionPane.WARNING_MESSAGE);
                            inventory.removeAdmin(admin);
                            Point location = getLocation();
                            int x = (int) location.getX();
                            int y = (int) location.getY();
//                            GUI.Customer.main(null, x, y, inventory, a);
//                            dispose();
                        } else {
                            passwordText.setText("");
                            JOptionPane.showMessageDialog(signInButton, "The Password Is wrong.🤨",
                                    "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }else {
                    userNameText.setText("");
                    passwordText.setText("");
                    JOptionPane.showMessageDialog(signInButton, "How should i know if you are a customer or admin you piece of shit",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        showPass.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    passwordText.setEchoChar((char) 0);
                } else {
                    passwordText.setEchoChar('\u2022');
                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point location = getLocation();
                int x = (int) location.getX();
                int y = (int) location.getY();
                SignUp.main(null, x, y, customersLinkedList, adminLinkedList, inventory);
                dispose();
            }
        });
    }

    public static void main(String[] args){
        SignIn signIn = new SignIn();
        signIn.setContentPane(signIn.mainPanel);
        signIn.setTitle("Sign In");
        signIn.setSize(500, 400);
        signIn.setResizable(false);
        signIn.setVisible(true);
        signIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void view(String[] args, int x, int y, Inventory inventory){
        SignIn signIn = new SignIn();
        signIn.setContentPane(signIn.mainPanel);
        signIn.setTitle("Sign In");
        signIn.setSize(500, 400);
        signIn.setLocation(x, y);
        signIn.setResizable(false);
        signIn.setVisible(true);
        signIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
