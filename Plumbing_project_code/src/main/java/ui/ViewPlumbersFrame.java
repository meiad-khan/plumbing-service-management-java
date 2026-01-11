package ui;

import db.PlumberDAO;
import model.Plumber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;



public class ViewPlumbersFrame extends JFrame {

    private JTable tbl;
    private DefaultTableModel model;
    private Color primary = new Color(12, 43, 78);
    private Color white = Color.WHITE;

    public ViewPlumbersFrame() {
        setTitle("All Plumbers");
        setSize(700, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(700, 60));
        JLabel lbl = new JLabel("Plumbers");
        lbl.setForeground(white); lbl.setFont(new Font("Arial", Font.BOLD, 18));
        header.add(lbl);
        add(header, BorderLayout.NORTH);

        String[] cols = {"Plumber ID", "Name", "Phone", "Experience", "Specialization", "Availability"};
        model = new DefaultTableModel(cols, 0);
        tbl = new JTable(model);
        tbl.setRowHeight(26);
        add(new JScrollPane(tbl), BorderLayout.CENTER);

        loadPlumbers();
    }

    private void loadPlumbers() {
        PlumberDAO dao = new PlumberDAO();

       
        ArrayList<Plumber> list = new ArrayList<>();

        try {
            list = dao.getAllPlumbers(); // preferred
        } catch (Throwable t) {
            list = dao.getAvailablePlumbers(); // fallback
        }

        model.setRowCount(0);
        for (Plumber p : list) {
            model.addRow(new Object[]{
                    p.getPlumberId(),
                    p.getName(),
                    p.getPhone(),
                    p.getExperience(),
                    p.getSpecialization(),
                    p.getAvailability()
            });
        }
    }
}
