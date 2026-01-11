package ui;

import db.JobDAO;
import db.PlumberDAO;
import model.Job;
import model.Plumber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PlumberDashboard extends JFrame {

    private int plumberId;
    private String plumberName;

    private JLabel lblTitle;
    private JTable tblJobs;
    private JButton btnRefresh, btnUpdateStatus, btnSetAvailable, btnSetBusy, btnLogout;
    private JScrollPane scroll;

    Color primary   = new Color(12, 43, 78);
    Color secondary = new Color(26, 61, 100);
    Color white     = Color.WHITE;

    public PlumberDashboard(int plumberId, String plumberName) {

        this.plumberId = plumberId;
        this.plumberName = plumberName;

        setTitle("Plumber Dashboard");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ------------ HEADER ------------
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(850, 80));

        lblTitle = new JLabel("Welcome, " + plumberName);
        lblTitle.setForeground(white);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnLogout = new JButton("Logout");
        btnLogout.setBackground(white);
        btnLogout.setForeground(primary);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.addActionListener(e -> {
            this.dispose();
            new LoginFrame().setVisible(true);
        });

        header.add(lblTitle, BorderLayout.WEST);
        header.add(btnLogout, BorderLayout.EAST);

        // ------------ TABLE ------------
        String[] cols = {"Job ID", "Customer ID", "Service ID", "Date", "Status", "Cost"};
        tblJobs = new JTable(new DefaultTableModel(cols, 0));
        tblJobs.setRowHeight(26);
        scroll = new JScrollPane(tblJobs);

        // ------------ BUTTON PANEL ------------
        JPanel actions = new JPanel();
        actions.setBackground(secondary);
        actions.setPreferredSize(new Dimension(850, 80));

        btnRefresh = new JButton("Refresh Jobs");
        btnUpdateStatus = new JButton("Update Status to Completed");
        btnSetAvailable = new JButton("Set Available");
        btnSetBusy = new JButton("Set Busy");

        JButton[] btns = {btnRefresh, btnUpdateStatus, btnSetAvailable, btnSetBusy};

        for (JButton b : btns) {
            b.setBackground(white);
            b.setForeground(primary);
            b.setFont(new Font("Arial", Font.BOLD, 13));
            b.setFocusPainted(false);
            actions.add(b);
        }

        // ------------ BUTTON FUNCTIONS ------------

        btnRefresh.addActionListener(e -> loadJobs());

        btnSetAvailable.addActionListener(e -> {
            PlumberDAO dao = new PlumberDAO();
            dao.updateAvailability(plumberId, "Available");
            JOptionPane.showMessageDialog(this, "Availability set to Available");
        });

        btnSetBusy.addActionListener(e -> {
            PlumberDAO dao = new PlumberDAO();
            dao.updateAvailability(plumberId, "Busy");
            JOptionPane.showMessageDialog(this, "Availability set to Busy");
        });

        btnUpdateStatus.addActionListener(e -> {
            int row = tblJobs.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a job first!");
                return;
            }

            int jobId = Integer.parseInt(tblJobs.getValueAt(row, 0).toString());

            JobDAO jobDAO = new JobDAO();
            jobDAO.updateStatus(jobId, "Completed");

            JOptionPane.showMessageDialog(this, "Job marked as Completed!");
            loadJobs();
        });

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);

        loadJobs();
    }

    private void loadJobs() {
        JobDAO jobDAO = new JobDAO();
        ArrayList<Job> list = jobDAO.getJobsByPlumber(plumberId);

        DefaultTableModel model = (DefaultTableModel) tblJobs.getModel();
        model.setRowCount(0);

        for (Job job : list) {
            model.addRow(new Object[]{
                    job.getJobId(),
                    job.getCustomerId(),
                    job.getServiceId(),
                    job.getScheduleDate(),
                    job.getStatus(),
                    job.getFinalCost()
            });
        }
    }
}
