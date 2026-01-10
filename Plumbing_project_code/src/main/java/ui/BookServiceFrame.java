package ui;

import db.JobDAO;
import db.ServiceDAO;
import model.Job;
import model.Service;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;


public class BookServiceFrame extends JFrame {

    private int customerId;
    private JLabel lblTitle, lblService, lblDate, lblError;
    private JComboBox<String> cmbServices;
    private JTextField txtDate;
    private JButton btnBook, btnCancel;

   
    private ArrayList<Service> serviceList = new ArrayList<>();

    public BookServiceFrame(int customerId) {
        this.customerId = customerId;
        setTitle("Book Service");
        setSize(480, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header
        lblTitle = new JLabel("Book New Service");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        // Center panel with form fields
        JPanel center = new JPanel(new GridBagLayout());
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);
        gc.anchor = GridBagConstraints.WEST;

        lblService = new JLabel("Service:");
        gc.gridx = 0;
        gc.gridy = 0;
        center.add(lblService, gc);

        cmbServices = new JComboBox<>();
        cmbServices.setPreferredSize(new Dimension(260, 26));
        gc.gridx = 1;
        center.add(cmbServices, gc);

        lblDate = new JLabel("Schedule (YYYY-MM-DD):");
        gc.gridx = 0;
        gc.gridy = 1;
        center.add(lblDate, gc);

        txtDate = new JTextField();
        txtDate.setPreferredSize(new Dimension(260, 26));
        gc.gridx = 1;
        center.add(txtDate, gc);

        lblError = new JLabel("");
        lblError.setForeground(Color.RED);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        center.add(lblError, gc);

        add(center, BorderLayout.CENTER);

        // Buttons panel
        JPanel btnPanel = new JPanel();
        btnBook = new JButton("Book");
        btnCancel = new JButton("Cancel");

        btnBook.addActionListener(e -> onBook());
        btnCancel.addActionListener(e -> dispose());

        btnPanel.add(btnBook);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);

        // Load services into combo
        loadServices();
    }

    
    
    
    
    
    private void loadServices() {
        ServiceDAO serviceDAO = new ServiceDAO();
        serviceList = serviceDAO.getAllServices();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Service s : serviceList) {
            
            String display = s.getServiceId() + " - " + s.getServiceType() + " (" + s.getBaseCost() + ")";
            model.addElement(display);
        }
        cmbServices.setModel(model);

        if (serviceList.isEmpty()) {
            lblError.setText("No services found. Ask admin to add services.");
            btnBook.setEnabled(false);
        } else {
            lblError.setText("");
            btnBook.setEnabled(true);
        }
    }

    
    
    
    
    private void onBook() {
        lblError.setText("");

        int selectedIndex = cmbServices.getSelectedIndex();
        if (selectedIndex < 0 || selectedIndex >= serviceList.size()) {
            lblError.setText("Please select a service.");
            return;
        }

        String dateText = txtDate.getText().trim();
        if (dateText.isEmpty()) {
            lblError.setText("Please enter schedule date (YYYY-MM-DD).");
            return;
        }

        // Validate date format by trying to convert to java.sql.Date
        Date scheduleDate;
        try {
            scheduleDate = Date.valueOf(dateText); 
        } catch (IllegalArgumentException ex) {
            lblError.setText("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        //asdf
        Service chosen = serviceList.get(selectedIndex);
        Job job = new Job(customerId, chosen.getServiceId(), scheduleDate, "Pending");

        JobDAO jobDAO = new JobDAO();
        boolean ok = jobDAO.createJob(job);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Job booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); 
        } else {
            lblError.setText("Failed to create job. Try again.");
        }
    }
}