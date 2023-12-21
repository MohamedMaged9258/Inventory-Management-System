package GUI;

import Classes.LinkedList;
import Classes.Node;
import Classes.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Products extends JFrame {
    private JPanel mainPanel;
    private JTable table;

    public Products(LinkedList productsList) {
        table = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        String[] columnNames = {"Id", "Name", "Price"};
        for (String s : columnNames) {
            TableColumn newColumn = new TableColumn();
            newColumn.setHeaderValue(columnNames);
            table.addColumn(newColumn);
        }

        Node node = productsList.getHead();
        while (node != null) {
            Object obj = node.getData();
            if (obj instanceof Product) {
                Product product = (Product) obj;
                String[] rowData = new String[3];
                rowData[0] = String.valueOf(product.getProductId());
                rowData[1] = product.getProductName();
                rowData[2] = String.valueOf(product.getPrice());
                tableModel.addRow(rowData);
            }
            node = node.getNext();
        }
        setContentPane(mainPanel);
    }

    public static void main(String[] args, int x, int y, LinkedList productsList, String title) {
        Products products = new Products(productsList);
        products.setContentPane(products.mainPanel);
        products.setTitle(title);
        products.setSize(500, 400);
        products.setLocation(x, y);
        products.setResizable(false);
        products.setVisible(true);
        products.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
