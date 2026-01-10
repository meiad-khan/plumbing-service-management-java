package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JButton btnAddPlumber, btnViewPlumbers, btnViewCustomers, btnAssignJob, btnLogout, btnAddService;
    private JLabel lblTitle;

    Color primary   = new Color(12, 43, 78);
    Color secondary = new Color(26, 61, 100);
    Color white     = Color.WHITE;

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ------------ HEADER ------------
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(800, 80));

        lblTitle = new JLabel("Admin Dashboard");
        lblTitle.setForeground(white);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnLogout = new JButton("Logout");
        btnLogout.setBackground(white);
        btnLogout.setForeground(primary);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });

        header.add(lblTitle, BorderLayout.WEST);
        header.add(btnLogout, BorderLayout.EAST);

        // ------------ CENTER PANEL ------------
        JPanel center = new JPanel();
        center.setBackground(secondary);

        btnAddPlumber = new JButton("Add Plumber");
        btnViewPlumbers = new JButton("View All Plumbers");
        btnViewCustomers = new JButton("View All Customers");
        btnAssignJob = new JButton("Assign Plumber To Job");
        btnAddService = new JButton("Add Service");

        JButton[] btns = {btnAddPlumber, btnViewPlumbers, btnViewCustomers, btnAssignJob, btnAddService};

        for (int i=0; i<5; i++) {
            btns[i].setPreferredSize(new Dimension(250, 50));
            btns[i].setBackground(white);
            btns[i].setForeground(primary);
            btns[i].setFont(new Font("Arial", Font.BOLD, 14));
            btns[i].setFocusPainted(false);
            center.add(btns[i]);
        }

        
        
        
        
        
        
        
        btnAddPlumber.addActionListener(e -> new AddPlumberFrame().setVisible(true));
        btnViewPlumbers.addActionListener(e -> new ViewPlumbersFrame().setVisible(true));
        btnViewCustomers.addActionListener(e -> new ViewCustomersFrame().setVisible(true));
        btnAssignJob.addActionListener(e -> new AssignPlumberFrame().setVisible(true));
        

btnAddService.addActionListener(e -> new AddServiceFrame().setVisible(true));


        add(header, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
    }
}