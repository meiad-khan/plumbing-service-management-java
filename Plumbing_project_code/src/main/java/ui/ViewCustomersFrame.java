package ui;

import db.CustomerDAO;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * ViewCustomersFrame - show all registered customers
 */
public class ViewCustomersFrame extends JFrame {

    private JTable tbl;
    private DefaultTableModel model;
    private Color primary = new Color(12, 43, 78);
    private Color white = Color.WHITE;

    public ViewCustomersFrame() {
        setTitle("All Customers");
        setSize(700, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(700, 60));
        JLabel lbl = new JLabel("Customers");
        lbl.setForeground(white); lbl.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(lbl);
        add(header, BorderLayout.NORTH);

        String[] cols = {"Customer ID", "Name", "Phone", "Address"};
        model = new DefaultTableModel(cols, 0);
        tbl = new JTable(model);
        tbl.setRowHeight(26);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        loadCustomers();
    }

    private void loadCustomers() {
        CustomerDAO dao = new CustomerDAO();
        ArrayList<Customer> list = dao.getAllCustomers(); // requires method added below
        model.setRowCount(0);
        for (Customer c : list) {
            model.addRow(new Object[]{
                    c.getCustomerId(),
                    c.getName(),
                    c.getPhone(),
                    c.getAddress()
            });
        }
    }
}
