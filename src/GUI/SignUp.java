package GUI;

import Classes.Admin;
import Classes.Customer;
import Classes.Inventory;
import Classes.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SignUp extends JFrame {
    ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel mainPanel;
    private JTextField nameText;
    private JTextField userNameText;
    private JRadioButton customerRadioButton;
    private JRadioButton adminRadioButton;
    private JButton signUpButton;
    private JButton signInButton;
    private JPasswordField passwordText;
    private JCheckBox viewCheckBox;

    public SignUp(LinkedList customersLinkedList, LinkedList adminLinkedList, Inventory inventory) {
        buttonGroup.add(customerRadioButton);
        buttonGroup.add(adminRadioButton);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point location = getLocation();
                int x = (int) location.getX();
                int y = (int) location.getY();
                SignIn.view(null, x, y, inventory);
                dispose();
            }
        });
        viewCheckBox.addItemListener(new ItemListener() {
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
                if (customerRadioButton.isSelected()) {
                    Object object = customersLinkedList.searchCustomerByUserName(userNameText.getText(), customersLinkedList.getHead());
                    if (!(object instanceof String)) {
                        userNameText.setText("");
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(signUpButton, "Sorry this User Name isn't Available.",
                                "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Customer customer = new Customer(userNameText.getText(), passwordText.getText(), nameText.getText(), new LinkedList());
                        Point location = getLocation();
                        int x = (int) location.getX();
                        int y = (int) location.getY();
                        GUI.Customer.main(null, x, y, inventory, customer);
                        dispose();
                    }
                } else if (adminRadioButton.isSelected()) {
                    Object object = adminLinkedList.searchAdminByUserName(userNameText.getText(), adminLinkedList.getHead());
                    if (object instanceof String) {
                        userNameText.setText("");
                        passwordText.setText("");
                        JOptionPane.showMessageDialog(signUpButton, "Sorry this User Name isn't Available.",
                                "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Admin admin = new Admin(userNameText.getText(), passwordText.getText(), nameText.getText());
                    }
                }
            }
        });
    }

    public static void main(String[] args, int x, int y, LinkedList customersLinkedList, LinkedList adminLinkedList, Inventory inventory) {
        SignUp signUp = new SignUp(customersLinkedList, adminLinkedList, inventory);
        signUp.setContentPane(signUp.mainPanel);
        signUp.setTitle("Sign Up");
        signUp.setSize(500, 400);
        signUp.setLocation(x, y);
        signUp.setResizable(false);
        signUp.setVisible(true);
        signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
