package ui;

import db.ServiceDAO;
import model.Service;
import javax.swing.*;
import java.awt.*;
//demo comment
public class AddServiceFrame extends JFrame {

    private JTextField txtType, txtCost;
    private JTextArea txtDescription;
    private JButton btnSave;

    Color primary = new Color(12, 43, 78);
    Color white   = Color.WHITE;

    public AddServiceFrame() {

        setTitle("Add New Service");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblType = new JLabel("Service Type:");
        lblType.setBounds(40, 30, 120, 25);
        add(lblType);

        txtType = new JTextField();
        txtType.setBounds(170, 30, 250, 28);
        add(txtType);

        JLabel lblCost = new JLabel("Base Cost:");
        lblCost.setBounds(40, 80, 120, 25);
        add(lblCost);

        txtCost = new JTextField();
        txtCost.setBounds(170, 80, 250, 28);
        add(txtCost);

        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setBounds(40, 130, 120, 25);
        add(lblDesc);

        txtDescription = new JTextArea();
        txtDescription.setBounds(170, 130, 250, 100);
        txtDescription.setLineWrap(true);
        add(txtDescription);

        btnSave = new JButton("Add Service");
        btnSave.setBounds(170, 260, 150, 35);
        btnSave.setBackground(primary);
        btnSave.setForeground(white);
        btnSave.setFocusPainted(false);

        btnSave.addActionListener(e -> saveService());
        add(btnSave);
    }

    private void saveService() {

        String type = txtType.getText().trim();
        String costStr = txtCost.getText().trim();
        String desc = txtDescription.getText().trim();

        if (type.isEmpty() || costStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Service type & cost required!");
            return;
        }

        double cost;
    try {
        cost = Double.parseDouble(costStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Base cost must be a valid number!");
        return;
    }

        Service service = new Service(type, desc, cost);

        ServiceDAO dao = new ServiceDAO();
        boolean ok = dao.addService(service);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Service Added Successfully!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to Add Service!");
        }
    }
}