package ui;

import db.JobDAO;
import db.PlumberDAO;
import model.Job;
import model.Plumber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;




public class AssignPlumberFrame extends JFrame {

    private JTable tblJobs;
    private DefaultTableModel jobsModel;
    private JComboBox<String> cmbPlumbers;
    private JButton btnAssign, btnRefresh;
    private ArrayList<Job> pendingJobs = new ArrayList<>();
    private ArrayList<Plumber> availablePlumbers = new ArrayList<>();
    private Color primary = new Color(12, 43, 78);
    private Color white = Color.WHITE;

    public AssignPlumberFrame() {
        setTitle("Assign Plumber To Job");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(900, 60));
        JLabel lbl = new JLabel("Assign Plumber");
        lbl.setForeground(white); lbl.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(lbl);
        add(header, BorderLayout.NORTH);

        // Jobs table
        String[] cols = {"Job ID", "Customer ID", "Service ID", "Date", "Status", "Plumber ID"};
        jobsModel = new DefaultTableModel(cols, 0);
        tblJobs = new JTable(jobsModel);
        tblJobs.setRowHeight(26);

        JPanel center = new JPanel(new BorderLayout());
        center.add(new JScrollPane(tblJobs), BorderLayout.CENTER);

        JPanel south = new JPanel();
        south.add(new JLabel("Select Plumber:"));
        cmbPlumbers = new JComboBox<>();
        south.add(cmbPlumbers);

        btnAssign = new JButton("Assign");
        btnRefresh = new JButton("Refresh");

        south.add(btnAssign);
        south.add(btnRefresh);

        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> loadData());
        btnAssign.addActionListener(e -> onAssign());

        loadData();
    }

    private void loadData() {
       
        
        JobDAO jobDAO = new JobDAO();
        pendingJobs = jobDAO.getPendingJobs(); // requires DAO method

        jobsModel.setRowCount(0);
        for (Job j : pendingJobs) {
            jobsModel.addRow(new Object[]{
                    j.getJobId(),
                    j.getCustomerId(),
                    j.getServiceId(),
                    j.getScheduleDate(),
                    j.getStatus(),
                    j.getPlumberId() == 0 ? "Not Assigned" : j.getPlumberId()
            });
        }

        // Load available plumbers
        PlumberDAO plumberDAO = new PlumberDAO();
        availablePlumbers = plumberDAO.getAvailablePlumbers();
        cmbPlumbers.removeAllItems();
        for (Plumber p : availablePlumbers) {
            cmbPlumbers.addItem(p.getPlumberId() + " - " + p.getName());
        }
        if (availablePlumbers.isEmpty()) {
            cmbPlumbers.addItem("No plumbers available");
            cmbPlumbers.setEnabled(false);
            btnAssign.setEnabled(false);
        } else {
            cmbPlumbers.setEnabled(true);
            btnAssign.setEnabled(true);
        }
    }

    private void onAssign() {
        int row = tblJobs.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a job from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (availablePlumbers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No plumbers available to assign.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int jobIndex = row;
        Job job = pendingJobs.get(jobIndex);

        int plumberIndex = cmbPlumbers.getSelectedIndex();
        if (plumberIndex < 0 || plumberIndex >= availablePlumbers.size()) {
            JOptionPane.showMessageDialog(this, "Please select a plumber.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Plumber plumber = availablePlumbers.get(plumberIndex);

        JobDAO jobDAO = new JobDAO();
        boolean assigned = jobDAO.assignPlumber(job.getJobId(), plumber.getPlumberId());

        if (assigned) {
            // set plumber to Busy
            PlumberDAO plumberDAO = new PlumberDAO();
            plumberDAO.updateAvailability(plumber.getPlumberId(), "Busy");

            JOptionPane.showMessageDialog(this, "Plumber assigned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "Assignment failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}