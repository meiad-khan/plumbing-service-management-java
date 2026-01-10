package ui;

import db.PlumberDAO;
import model.Plumber;

import javax.swing.*;
import java.awt.*;




public class AddPlumberFrame extends JFrame {

    private JTextField txtName, txtPhone, txtExperience, txtSpecialization;
    private JComboBox<String> cmbAvailability;
    private JButton btnAdd, btnCancel;
    private Color primary = new Color(12, 43, 78);
    private Color secondary = new Color(26, 61, 100);
    private Color white = Color.WHITE;

    public AddPlumberFrame() {
        setTitle("Add Plumber");
        setSize(420, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(420, 70));
        JLabel lbl = new JLabel("Add New Plumber");
        lbl.setForeground(white);
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.add(lbl);
        add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx = 0; gc.gridy = 0; center.add(new JLabel("Name:"), gc);
        txtName = new JTextField(20); gc.gridx = 1; center.add(txtName, gc);

        gc.gridx = 0; gc.gridy++; center.add(new JLabel("Phone:"), gc);
        txtPhone = new JTextField(20); gc.gridx = 1; center.add(txtPhone, gc);

        gc.gridx = 0; gc.gridy++; center.add(new JLabel("Experience (years):"), gc);
        txtExperience = new JTextField(5); gc.gridx = 1; center.add(txtExperience, gc);

        gc.gridx = 0; gc.gridy++; center.add(new JLabel("Specialization:"), gc);
        txtSpecialization = new JTextField(20); gc.gridx = 1; center.add(txtSpecialization, gc);

        gc.gridx = 0; gc.gridy++; center.add(new JLabel("Availability:"), gc);
        cmbAvailability = new JComboBox<>(new String[]{"Available", "Busy", "On Leave"});
        gc.gridx = 1; center.add(cmbAvailability, gc);

        add(center, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        btnAdd = new JButton("Add Plumber");
        btnAdd.setBackground(primary); btnAdd.setForeground(white);
        btnCancel = new JButton("Cancel");
        footer.add(btnAdd); footer.add(btnCancel);
        add(footer, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> onAddPlumber());
    }

    private void onAddPlumber() {
    String name = txtName.getText().trim();
    String phone = txtPhone.getText().trim();
    String expText = txtExperience.getText().trim();
    String spec = txtSpecialization.getText().trim();
    String availability = cmbAvailability.getSelectedItem().toString();

    if (name.isEmpty() || phone.isEmpty() || expText.isEmpty() || spec.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int exp;
    try {
        exp = Integer.parseInt(expText);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Experience must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // DEFAULT PASSWORD FOR ALL PLUMBERS
    String defaultPassword = "1234";

    Plumber plumber = new Plumber(name, phone, exp, spec, availability, defaultPassword);

    PlumberDAO dao = new PlumberDAO();
    boolean ok = dao.addPlumber(plumber);

    if (ok) {
        JOptionPane.showMessageDialog(this, 
            "Plumber added successfully.\nDefault Password: 1234", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);

        dispose();
    } else {
        JOptionPane.showMessageDialog(this, 
            "Failed to add plumber. Phone may already exist.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

}