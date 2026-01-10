package ui;

import db.JobDAO;
import db.PlumberDAO;
import db.ServiceDAO;
import model.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class CustomerDashboard extends JFrame {

    private int customerId; 
    
    private JLabel lblTitle, lblError;
    private JButton btnBookService, btnRefresh, btnLogout;
    private JTable tblJobs;
    private JScrollPane scrollPane;

    private Color primary = new Color(12, 43, 78);
    private Color secondary = new Color(26, 61, 100);

    public CustomerDashboard(int customerId, String customerName) {
        this.customerId = customerId;

        setTitle("Customer Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ------------------ TOP HEADER PANEL -----------------------
        JPanel topPanel = new JPanel();
        topPanel.setBackground(primary);
        topPanel.setPreferredSize(new Dimension(800, 80));
        topPanel.setLayout(new BorderLayout());

        lblTitle = new JLabel("Welcome, " + customerName);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnLogout = new JButton("Logout");
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setForeground(primary);
        btnLogout.setFocusPainted(false);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        topPanel.add(lblTitle, BorderLayout.WEST);
        topPanel.add(btnLogout, BorderLayout.EAST);

        // ------------------ MIDDLE BUTTON PANEL -----------------------
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(secondary);
        middlePanel.setPreferredSize(new Dimension(800, 80));

        btnBookService = new JButton("Book New Service");
        btnBookService.setFont(new Font("Arial", Font.BOLD, 14));
        btnBookService.setBackground(Color.WHITE);
        btnBookService.setForeground(primary);
        btnBookService.setFocusPainted(false);
        btnBookService.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBookService.addActionListener(e -> {
            new BookServiceFrame(customerId).setVisible(true);
        });

        btnRefresh = new JButton("Refresh Jobs");
        btnRefresh.setFont(new Font("Arial", Font.BOLD, 14));
        btnRefresh.setBackground(Color.WHITE);
        btnRefresh.setForeground(primary);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnRefresh.addActionListener(e -> loadJobs());

        middlePanel.add(btnBookService);
        middlePanel.add(btnRefresh);

        // ------------------ TABLE PANEL -----------------------
        String[] columns = {"Job ID", "Service ID", "Plumber ID", "Date", "Status", "Cost"};

        tblJobs = new JTable(new DefaultTableModel(columns, 0));
        tblJobs.setFont(new Font("Arial", Font.PLAIN, 14));
        tblJobs.setRowHeight(25);

        scrollPane = new JScrollPane(tblJobs);

        // ------------------ ERROR LABEL -----------------------
        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);

        // ------------------ ADDING PANELS -----------------------
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        add(lblError, BorderLayout.AFTER_LAST_LINE);

        loadJobs(); 
    }

    
    private void loadJobs() {
        JobDAO jobDAO = new JobDAO();
        ArrayList<Job> jobList = jobDAO.getJobsByCustomer(customerId);

        DefaultTableModel model = (DefaultTableModel) tblJobs.getModel();
        model.setRowCount(0); // clear table first

        for (Job job : jobList) {
            model.addRow(new Object[]{
                    job.getJobId(),
                    job.getServiceId(),
                    job.getPlumberId() == 0 ? "Not Assigned" : job.getPlumberId(),
                    job.getScheduleDate(),
                    job.getStatus(),
                    job.getFinalCost()
            });
        }

        if (jobList.isEmpty()) {
            lblError.setText("No jobs found.");
        } else {
            lblError.setText("");
        }
    }
}
//fghjk